package cn.tedu.csmall.stock.webapi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//mybatis框架扫描mapper包接口的注解
@MapperScan("cn.tedu.csmall.stock.webapi.mapper")
public class MybatisConfiguration {
}
