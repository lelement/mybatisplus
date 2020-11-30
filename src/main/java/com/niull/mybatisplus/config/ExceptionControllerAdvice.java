package com.niull.mybatisplus.config;


import com.niull.mybatisplus.common.JsonResult;
import com.niull.mybatisplus.enums.ResultCode;
import com.niull.mybatisplus.exception.APIException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author niull
 * @Date 2020/10/26 20:33
 * @Description 牛立露你就写点注释吧
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(APIException.class)
    public JsonResult<String> APIExceptionHandler(APIException e) {
        return JsonResult.errorMsg(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return JsonResult.error(ResultCode.VALIDATE_FAILED,objectError.getDefaultMessage());
    }

}
