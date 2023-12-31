package com.example.b2win.refreshtoken.service;

import com.example.b2win.config.jwt.TokenProvider;
import com.example.b2win.refreshtoken.dto.TokenDto;
import com.example.b2win.user.service.UserService;
import lombok.RequiredArgsConstructor;
import com.example.b2win.user.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public TokenDto createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if (!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.createAllToken(user);
    }
}

