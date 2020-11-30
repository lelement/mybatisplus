package com.niull.mybatisplus.service;

import com.niull.mybatisplus.dto.UserDto;
import com.niull.mybatisplus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author niull
 * @since 2020-11-30
 */
public interface IUserService extends IService<User> {

    Integer addUser(User user);

    Integer updateUser(User user) throws InterruptedException;

    UserDto login(String account, String password);

    Integer deleteUserById(Long id);
}
