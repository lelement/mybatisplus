package com.niull.mybatisplus.common;


import com.niull.mybatisplus.enums.ResultCode;

/**
 * @Author niull
 * @Date 2020/10/10 15:29
 * @Description 牛立露你就写点注释吧
 */
public class JsonResult<T> {
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;


    public static JsonResult build(Integer status, String msg, Object data) {
        return new JsonResult(status, msg, data);
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(data);
    }

    public static JsonResult ok() {
        return new JsonResult(null);
    }

    public static JsonResult errorMsg(String msg) {
        return new JsonResult(5000, msg, null);
    }

    public static JsonResult errorMsg(Integer code, String msg) {
        return new JsonResult(code, msg, null);
    }


    public static JsonResult error(ResultCode resultCode, String data) {
        return new JsonResult(resultCode.getCode(), resultCode.getMsg(), data);
    }

    public JsonResult() {

    }

    public JsonResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult(Object data) {
        this.status = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
