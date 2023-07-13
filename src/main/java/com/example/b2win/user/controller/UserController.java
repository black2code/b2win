package com.example.b2win.user.controller;

import com.example.b2win.config.jwt.TokenProvider;
import com.example.b2win.global.dto.GlobalResDto;
import com.example.b2win.refreshtoken.dto.TokenDto;
import com.example.b2win.user.domain.User;
import com.example.b2win.user.dto.AddUserRequest;
import com.example.b2win.user.dto.LoginRequest;
import com.example.b2win.user.mapper.UserMapperClass;
import com.example.b2win.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final UserMapperClass mapper;

    @PostMapping("/signup")
    @Operation(summary = "회원 가입 api")
    public ResponseEntity<GlobalResDto> signUp(@RequestBody @Valid AddUserRequest request) {
        User user = mapper.userCreateDtoToUser(request);
        userService.createUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new GlobalResDto("Success signUp", HttpStatus.CREATED.value()));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인 api")
    public ResponseEntity<GlobalResDto> login(@RequestBody @Valid LoginRequest request) {
        TokenDto tokenDto = userService.login(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(TokenProvider.ACCESS_TOKEN, tokenDto.getAccessToken())
                .header(TokenProvider.REFRESH_TOKEN, tokenDto.getRefreshToken())
                .body(new GlobalResDto("Success Login", HttpStatus.OK.value()));
    }

//    @DeleteMapping("/user")
//    @ApiOperation(value = "유저 탈퇴")
//    public ResponseEntity deleteUser(HttpServletRequest request) {
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
}
