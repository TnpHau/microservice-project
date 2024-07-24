package com.tnphuchau.productservice.application.common;

import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS("00", "Thành công"),
    NOT_FOUND("05", "Không tồn tại"),
    EMAIL_OR_USERNAME_ALREADY_EXISTS("400", "Email or Username is already taken!"),
    ACCOUNT_OR_PASSWORD_IS_INCORRECT("99","Account or password is incorrect!"),
    SYSTEM_ERROR("99", "Lỗi hệ thống!"),
    LOGIN_REQUIRE("01", "Vui lòng đăng nhập!");

    private String code;
    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}