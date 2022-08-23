package cn.tedu.csmall.cart.webapi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//mybatis框架扫描mapper包接口的注解
@MapperScan("cn.tedu.csmall.cart.webapi.mapper")
public class MybatisConfiguration {
}
