package com.yang.basicmodel.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yang.basicmodel.entity.test.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisListTest {

    @Autowired
    RedisListService<String> basicType;
    
    @Autowired
    RedisListService<Student> multi;

    @Test
    public void TestBasicTest() {
        boolean isWrite = false;
        if (isWrite) {
            for (int i = 0; i < 10; i++) {
                basicType.push("BasicTestList", String.valueOf(i));
            }
        }
        String byindex = basicType.getByindex("BasicTestList", 0);
        System.out.println("RedisListTest.TestBasicTest():"+byindex);
        
        System.out.println("RedisListTest.TestBasicTest().size："+basicType.size("BasicTestList"));
        
        List<String> all = basicType.getAll("BasicTestList");
        System.out.println("RedisListTest.TestBasicTest().all："+all.toString());
    }
    
    @Test
    public void TestMulti(){
        boolean isWrite=true;
        if(isWrite){
            List<Student> sts = new ArrayList<>();
            for(int i = 0; i<10; i++){
                Student st = new Student();
                st.setAge(i);
                st.setCls("class"+i);
                multi.push("ListMulti", st);
                
                sts.add(st);
            }
            multi.pushAll("ListMultiAll", sts);
        }
        List<Student> all = multi.getAll("ListMulti");
        System.out.println("RedisListTest.TestMulti():"+all.toString());
        
        all = multi.getAll("ListMultiAll");
        System.out.println("RedisListTest.TestMulti():"+all.toString());
    }
     
}
