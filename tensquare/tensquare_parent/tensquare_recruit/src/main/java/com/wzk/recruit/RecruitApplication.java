package com.wzk.recruit;/*
 @author 吴梓康
 @create 2019/3/16
*/

import com.wzk.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RecruitApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class,args);
    }

    @Bean
    public IdWorker getIdWorker() {
        return new IdWorker();
    }
}
