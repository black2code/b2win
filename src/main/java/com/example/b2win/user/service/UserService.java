package com.example.b2win.user.service;


import com.example.b2win.config.jwt.TokenProvider;
import com.example.b2win.global.error.ErrorCode;
import com.example.b2win.refreshtoken.domain.RefreshToken;
import com.example.b2win.refreshtoken.dto.TokenDto;
import com.example.b2win.refreshtoken.repository.RefreshTokenRepository;
import com.example.b2win.user.dto.LoginRequest;
import com.example.b2win.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.b2win.user.domain.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;


    @Transactional
    public void createUser(User user) {
        userRepository.save(User.builder()
                .name(user.getName())
                .birthday(user.getBirthday())
                .sex(user.getSex())
                .account(user.getAccount())
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .build());
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    @Transactional(noRollbackFor = BadCredentialsException.class)
    public TokenDto login(LoginRequest request) {

        User user = userRepository.findByAccount(request.getAccount())
                .orElseThrow(() -> new IllegalArgumentException("Not found Account"));

        if (user.isLocked()) {
            throw new IllegalArgumentException("Account locked");
        }
        if (!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
            user.incrementPasswordErrorCount();
            if (user.getPasswordErrorCount() >= 5) {
                user.lockAccount();
            }
            userRepository.save(user);
            throw new BadCredentialsException(ErrorCode.NOTEQUAL_INPUT_PASSWORD.getDetail());
        }

        user.resetPasswordErrorCount();
        user.unlockAccount();
        userRepository.save(user);

        if (user.isPasswordExpired()) {
            throw new IllegalArgumentException("Password has expired");
        }

        TokenDto tokenDto = tokenProvider.createAllToken(user);

        Optional <RefreshToken> refreshToken = refreshTokenRepository.findByUserId(user.getId());

        if (refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().update(tokenDto.getRefreshToken()));
        } else {
            RefreshToken newToken = new RefreshToken(user.getId(), tokenDto.getRefreshToken());
            refreshTokenRepository.save(newToken);
        }

        return tokenDto;
    }

}
