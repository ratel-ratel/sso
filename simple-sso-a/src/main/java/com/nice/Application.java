package com.nice;


import com.nice.filter.MyFilterA;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBException;


@SpringBootApplication
@EnableCaching
@MapperScan("com.nice.mapper*")
@Slf4j
@Configuration
public class Application {

    public static void main(String[] args) throws JAXBException {
        SpringApplication application = new SpringApplication(
                Application.class);
        application.run(args);
    }

    /**
     * 配置,过滤器,过滤路径
     * @return
     */
    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilterA());//添加过滤器
        registration.addUrlPatterns("/*");//设置过滤路径，/*所有路径
        registration.setName("MyFilter");//设置优先级
        return registration;
    }
}
