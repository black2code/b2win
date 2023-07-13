package com.example.b2win.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(BAD_REQUEST, " Invalid Input Value"),
    METHOD_NOT_ALLOWED(BAD_REQUEST,  " Invalid Input Value"),
    ENTITY_NOT_FOUND(BAD_REQUEST,  " Entity Not Found"),
    INVALID_TYPE_VALUE(BAD_REQUEST, " Invalid Type Value"),

    // 유저
    HANDLE_ACCESS_DENIED(BAD_REQUEST, "로그인이 필요합니다."),
    INVALID_INPUT_USERNAME(BAD_REQUEST, "닉네임을 3자 이상 입력하세요"),
    NOTEQUAL_INPUT_PASSWORD(BAD_REQUEST,  "비밀번호가 일치하지 않습니다"),
    INVALID_PASSWORD(BAD_REQUEST,  "비밀번호를 4자 이상 입력하세요"),
    INVALID_USERNAME(BAD_REQUEST,  "알파벳 대소문자와 숫자로만 입력하세요"),
    NOT_AUTHORIZED(BAD_REQUEST, "작성자만 수정 및 삭제를 할 수 있습니다."),
    USERNAME_DUPLICATION(BAD_REQUEST, "이미 등록된 아이디입니다."),
    LOGIN_INPUT_INVALID(BAD_REQUEST, "로그인 정보를 다시 확인해주세요."),
    NOTFOUND_USER(BAD_REQUEST,  "해당 이름의 유저가 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
