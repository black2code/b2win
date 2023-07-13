package com.example.b2win.refreshtoken.controller;

import com.example.b2win.refreshtoken.dto.CreateAccessTokenRequest;
import com.example.b2win.refreshtoken.dto.TokenDto;
import com.example.b2win.refreshtoken.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TokenApiController {

    private final TokenService tokenService;

    @PostMapping("/api/token")
    @Operation(summary = "access token 발급")
    public ResponseEntity<TokenDto> createNewAccessToken(@RequestBody CreateAccessTokenRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tokenService.createNewAccessToken(request.getRefreshToken()));
    }
}
