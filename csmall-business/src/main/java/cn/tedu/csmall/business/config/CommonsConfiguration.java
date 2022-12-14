package cn.tedu.csmall.business.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 只有添加了@Configuration才能配置当前Spring环境
@Configuration
// 扫描commons模块的统一异常处理类,使其在项目中生效
@ComponentScan("cn.tedu.csmall.commons.exception")
public class CommonsConfiguration {

}
