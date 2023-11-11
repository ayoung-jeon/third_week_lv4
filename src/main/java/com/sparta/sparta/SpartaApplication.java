package com.sparta.sparta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // 등록일과 수정일 사용할때 필요
public class SpartaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpartaApplication.class, args);
    }

}
