package com.niull.mybatisplus.exception;


import com.niull.mybatisplus.enums.ResultCode;
import lombok.Getter;

/**
 * @Author niull
 * @Date 2020/10/26 20:22
 * @Description 牛立露你就写点注释吧
 */
@Getter //只要getter方法，无需setter
public class APIException extends RuntimeException {
    private int code;
    private String msg;

    public APIException() {
        this(1001, "接口错误");
    }

    public APIException(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMsg());
    }

    public APIException(String msg) {
        this(1001, msg);
    }

    public APIException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
