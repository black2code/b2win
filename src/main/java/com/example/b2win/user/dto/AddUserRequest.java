package com.example.b2win.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "생일은 필수 입력 값입니다.")
    private String birthday;

    @NotBlank(message = "성별은 필수 입력 값입니다.")
    private String sex;

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String account;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).{8,16}$", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "비밀번호는 숫자를 포함한 8~16자의 영문자여야 합니다.")
    private String password;

    @Email(regexp = ".*|.*@.*\\..*", message = "이메일 형식이 올바르지 않습니다.")
    private String email;
}
