package com.niull.mybatisplus.enums;

import lombok.Getter;

/**
 * @Author niull
 * @Date 2020/10/10 15:39
 * @Description 牛立露你就写点注释吧
 */
@Getter
public enum ResultCode {

    SUCCESS(2000, "操作成功"),

    RETURN_TYPE_ERROR(1000,"返回类型String错误"),

    FAILED(1001, "响应失败"),

    VALIDATE_FAILED(1002, "参数校验失败"),

    TOKEN_NOT_EXIST(1003,"token不存在！"),

    ACCOUNT_ALREADY_EXIST(1004,"账号已存在"),

    ACCOUNT_NOT_EXIST(1005,"账号不存在"),

    PASSWORD_ERROR(1006,"密码不正确"),

    ERROR(5000, "未知错误");

    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
