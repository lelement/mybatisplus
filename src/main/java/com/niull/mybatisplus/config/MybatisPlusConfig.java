package com.niull.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author niull
 * @Date 2020/11/30 13:23
 * @Description 牛立露你就写点注释吧
 */

//开启事务
@EnableTransactionManagement
@Configuration
@MapperScan("com.niull.mybatisplus.mapper")
public class MybatisPlusConfig {
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        OptimisticLockerInterceptor lockerInterceptor = new OptimisticLockerInterceptor();
        return lockerInterceptor;
    }
}

