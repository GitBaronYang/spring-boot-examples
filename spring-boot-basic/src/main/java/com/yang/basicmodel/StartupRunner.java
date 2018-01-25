package com.yang.basicmodel;


import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * spring boot启动执行类 可有多个执行类，Order注解设置执行顺序
 * @author klaus
 *
 */
@Component
@Order(value=1)
public class StartupRunner implements CommandLineRunner  {

    @Override
    public void run(String... args) throws Exception {
    	
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作  <<<<<<<<<<<<<");
        
    }
    
}
