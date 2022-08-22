package cn.tedu.csmall.stock.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//mybatis框架扫描mapper包接口的注解
@MapperScan("cn.tedu.csmall.stock.mapper")
public class MybatisConfiguration {
}
