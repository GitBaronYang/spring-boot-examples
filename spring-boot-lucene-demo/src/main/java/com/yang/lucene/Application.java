package com.yang.lucene;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述:
 *
 * @author: yanpenglei
 * @create: 2017/11/7 17:02
 */
@SpringBootApplication
@ComponentScan(value = {"com.yang.lucene"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
