package com.vietqr.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class VietqrBizServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VietqrBizServerApplication.class, args);
    }
}
