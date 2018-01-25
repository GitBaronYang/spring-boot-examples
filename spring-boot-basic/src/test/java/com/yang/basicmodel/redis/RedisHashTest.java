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

import com.yang.basicmodel.entity.Message;
import com.yang.basicmodel.entity.test.Student;
import com.yang.basicmodel.entity.test.TestSingle;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisHashTest {
    
    @Autowired
    RedisHashService<String> common;
    
    @Autowired
    RedisHashService<TestSingle> mixin;
    
    @Autowired
    RedisHashService<Student> multi;
    
    @Test
    public void TestCommon(){
        boolean isWrite=true;
        if(isWrite){
            common.put("maps", "name", "hello", -1);
            common.put("maps", "age", "12", -1);
        }
        String object = common.get("maps", "name");
        System.out.println("RedisHashTest.TestCommon():"+object.toString());
        object = common.get("maps", "age");
        System.out.println("RedisHashTest.TestCommon():"+object.toString());
    }
    
    @Test
    public void TestMixin(){
        boolean isWrite=false;
        if(isWrite){
            TestSingle testSingle = new TestSingle();
            //基本类型
            testSingle.setName("yang");
            testSingle.setHobby("音乐");
            testSingle.setAddress("乡村");
            //内部嵌套类
            Message message = new Message();
            message.setMessage("hello");
            //嵌套基本类型数组
            message.setNums(new int[]{1,2,3});
            //嵌套list
            List<Integer> its= new ArrayList<>();
            its.add(2);
            its.add(3);
            message.setIts(its);
            //再嵌套基本类
            Student student = new Student();
            student.setAge(2);
            message.setStudent(student);
            //再嵌套List<Student>
            List<Student> students = new ArrayList<>();
            for(int i= 0; i<10;i++){
                Student tmpStu = new Student();
                tmpStu.setAge(i);
                students.add(tmpStu);
            }
            message.setStudents(students);
            testSingle.setMessage(message);
            mixin.put("TestSingle", "key1", testSingle, -1);
        }
        TestSingle testSingle = mixin.get("TestSingle", "key1");
        System.out.println("RedisHashTest.TestMixin():"+testSingle.toString());
    }
    
    @Test
    public void TestMulti(){
        boolean isWrite=true;
        if(isWrite){
            Map<String,Student> students = new HashMap<>();
            for(int i = 0; i<10; i++){
                Student st = new Student();
                st.setAge(i);
                st.setCls("class"+i);
                students.put(String.valueOf(i), st);
            }
            multi.putAll("TestMulti", students, -1);
        }
        Map<String, Student> entries = multi.entries("TestMulti");
        System.out.println("RedisHashTest.TestMulti():"+entries.toString());
        
        List<String> keys = new  ArrayList<>();
        for(int i = 0; i<8; i++){
            keys.add(String.valueOf(i));
        }
        
        List<Student> multiGet = multi.multiGet("TestMulti", keys);
        System.out.println("RedisHashTest.multiGet():"+multiGet.toString());
        
        List<Student> all = multi.getAll("TestMulti");
        System.out.println("RedisHashTest.all():"+all.toString());
        
        boolean isDelete=false;
        if(isDelete){
            multi.remove("TestMulti", "2");
            multi.empty("TestMulti");
        }
    }
}
