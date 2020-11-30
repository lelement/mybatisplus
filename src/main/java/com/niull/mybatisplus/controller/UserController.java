package com.niull.mybatisplus.controller;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.niull.mybatisplus.common.JsonResult;
import com.niull.mybatisplus.dto.UserDto;
import com.niull.mybatisplus.entity.User;
import com.niull.mybatisplus.enums.ResultCode;
import com.niull.mybatisplus.exception.APIException;
import com.niull.mybatisplus.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author niull
 * @since 2020-11-30
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/mybatisplus/user")
public class UserController {
    @Resource
    private IUserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @ApiOperation(value = "添加用户")
    @PostMapping("/addUser")
    public Integer addUser(@RequestBody User user){
        return userService.addUser(user);
    }
    @ApiImplicitParam(name = "id",value = "id",required = true)
    @ApiOperation(value = "更新用户信息")
    @PutMapping("/updateUser")
    public Integer updateUser(@RequestHeader("token") String token,
                              @RequestBody User user) throws InterruptedException {
        if (token!=null && redisTemplate.opsForValue().get(token) != null) {
            return userService.updateUser(user);
        }else {
            throw new APIException(ResultCode.TOKEN_NOT_EXIST.getCode(),ResultCode.TOKEN_NOT_EXIST.getMsg());
        }
    }

    @GetMapping("/login")
    public UserDto login(@RequestParam(value = "account")
                             @ApiParam(name = "账号",
                                     defaultValue = "17764774380") String account,
                         @RequestParam(value = "password")
                                 @ApiParam(name = "密码",defaultValue = "123123") String password){
        return userService.login(account,password);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public Integer deleteUserById(@PathVariable(value = "userId")Long id){
        return userService.deleteUserById(id);
    }

    @GetMapping("/test")
    public String test(){
        return "helloWorld";
    }
}

