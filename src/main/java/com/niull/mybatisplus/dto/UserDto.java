package com.niull.mybatisplus.dto;

import com.niull.mybatisplus.entity.User;
import lombok.Data;

/**
 * @Author niull
 * @Date 2020/11/30 15:00
 * @Description 牛立露你就写点注释吧
 */
@Data
public class UserDto extends User {
    private String token;
}
