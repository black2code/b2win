package com.example.b2win.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String account;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).{8,16}$", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "비밀번호는 숫자를 포함한 8~16자의 영문자여야 합니다.")
    private String password;

}
