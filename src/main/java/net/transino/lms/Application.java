package net.transino.lms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lee
 * @since 5.0
 */
@SpringBootApplication
@MapperScan("net.transino.lms.web.mapper")
public class Application {
    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }
}