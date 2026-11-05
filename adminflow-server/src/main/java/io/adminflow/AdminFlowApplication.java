package io.adminflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("io.adminflow.modules.**.mapper")
public class AdminFlowApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminFlowApplication.class, args);
    }
}
