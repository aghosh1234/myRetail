package com.retail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration()
@EnableSwagger2
@ComponentScan({"com.retail"})
@ImportResource({"classpath:configCouchBaseDao.xml"})
public class Application {
    public static void main(String [] argv){
        SpringApplication.run(Application.class, argv);
    }
}
