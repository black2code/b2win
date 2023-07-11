package com.example.b2win.user.service;

import com.example.b2win.user.dto.AddUserRequest;
import com.example.b2win.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.b2win.user.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createUser(AddUserRequest dto) {
        User user = userRepository.save(User.builder()
                .name(dto.getName())
                .birthday(dto.getBirthday())
                .sex(dto.getSex())
                .account(dto.getAccount())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .build());
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
