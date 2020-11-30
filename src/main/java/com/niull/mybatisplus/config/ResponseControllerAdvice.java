package com.niull.mybatisplus.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niull.mybatisplus.common.JsonResult;
import com.niull.mybatisplus.enums.ResultCode;
import com.niull.mybatisplus.exception.APIException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author niull
 * @Date 2020/10/26 20:46
 * @Description 牛立露你就写点注释吧
 */
@RestControllerAdvice(basePackages = {"com.niull.mybatisplus.controller"}) // 注意哦，这里要加上需要扫描的包
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        return !returnType.getParameterType().equals(JsonResult.class);
    }

    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response) {
        // String类型不能直接包装，所以要进行些特别的处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResultVO里后，再转换为json字符串响应给前端
                return objectMapper.writeValueAsString(new JsonResult(data));
            } catch (JsonProcessingException e) {
                throw new APIException(ResultCode.RETURN_TYPE_ERROR);
            }
        }
        // 将原本的数据包装在ResultVO里
        return new JsonResult(data);
    }
}
