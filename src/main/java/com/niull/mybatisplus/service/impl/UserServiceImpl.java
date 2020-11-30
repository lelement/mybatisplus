package com.niull.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.niull.mybatisplus.dto.UserDto;
import com.niull.mybatisplus.entity.User;
import com.niull.mybatisplus.enums.ResultCode;
import com.niull.mybatisplus.exception.APIException;
import com.niull.mybatisplus.mapper.UserMapper;
import com.niull.mybatisplus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoleilu.hutool.json.JSONUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author niull
 * @since 2020-11-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Integer addUser(User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("account",user.getAccount());
        List<User> userList = userMapper.selectByMap(params);
        if (userList != null && userList.size() != 0) {
            return userMapper.insert(user);
        }else {
            throw new APIException(ResultCode.ACCOUNT_NOT_EXIST);
        }
    }

    @Override
    public Integer updateUser(User user) throws InterruptedException {
        User userTemp = userMapper.selectById(user.getId());
        user.setVersion(userTemp.getVersion());
        return userMapper.updateById(user);
    }

    @Override
    public UserDto login(String account, String password) {
        UserDto userDto = new UserDto();
        Map<String, Object> params = new HashMap<>();
        params.put("account",account);
        List<User> users = userMapper.selectByMap(params);
        if (users != null && users.size() != 0){
            User user = users.get(0);
            String pass = user.getPassword();
            if (password.equals(pass)){
                BeanUtils.copyProperties(user,userDto);
                String uuid = UUID.randomUUID().toString();
                String token = uuid + "," + user.getAccount();
                //将token留在redis中
                redisTemplate.opsForValue().set(token, JSONUtil.toJsonStr(user));
                redisTemplate.expire(token, 10, TimeUnit.DAYS);
                userDto.setToken(token);
            }else {
             throw new APIException(ResultCode.PASSWORD_ERROR);
            }
        }else {
            throw new APIException(ResultCode.ACCOUNT_NOT_EXIST);
        }
        return userDto;
    }

    @Override
    public Integer deleteUserById(Long id) {
        return userMapper.deleteById(id);
    }
}
