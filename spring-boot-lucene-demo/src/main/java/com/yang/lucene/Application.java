package com.yang.lucene;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/** 
* @author  
* @E-mail: 
* @date 创建时间：2018年1月25日 上午11:43:33 
* @version 1.0   
*/
@SpringBootApplication
@ComponentScan(value = {"com.yang.lucene"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
