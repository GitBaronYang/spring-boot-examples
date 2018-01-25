package com.yang.basicmodel.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yang.basicmodel.entity.Message;
import com.yang.basicmodel.entity.test.Student;
import com.yang.basicmodel.entity.test.TestSingle;
import com.yang.basicmodel.redis.RedisObjectService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisObjectTest {

    @Autowired
    RedisObjectService<TestSingle> redisObjectService;
    
    /**
     * 不推荐使用这种方式存储map结构数据，存储map使用RedisHashService，此处只是测是使用
     */
    @Autowired
    RedisObjectService<Map<String,String>> redismapService;
    /**
     * @功能描述 测试嵌套类数据
     * @param
     * @return
     */
    @Test
    public void TestObject(){
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
        boolean isWrite=false;
        if(isWrite){
            redisObjectService.set("testSingle", testSingle);
        }else{
            TestSingle testSingle2 = redisObjectService.get("testSingle");
            System.out.println("RedisTest.TestObject()："+testSingle2.toString());
        }
    }
    
    @Test
    public void testInc() throws InterruptedException{
        CountDownLatch latch = new CountDownLatch(100);
        ExecutorService cachedThreadPool = Executors.newFixedThreadPool(100);
        try {
            for(int i =0;i<100;i++){
                cachedThreadPool.execute(new Runnable() {
                    public void run() {
                        testIndex();
                        latch.countDown();
                        redisObjectService.LongIncrement("longinc", 5L);
                    }
               });
            }
            latch.await();
            System.out.println("-----------------------------------------");
            System.out.println(h.size());
            System.out.println("-----------------------------------------");
            System.out.println(h.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            cachedThreadPool.shutdown();
        }
    }
    Integer index =0;
    HashSet h=new HashSet();
    private void testIndex(){
        synchronized(RedisObjectTest.class){
            h.add(++index);
            System.out.println(index);
        }
    }
    /**
     * @功能描述 不推荐使用，只是为了测试
     * @param
     * @return
     */
    @Test
    public void testMapObject(){
        Map<String,String> maps = new HashMap<>();
        maps.put("1", "1");
        maps.put("2", "2");
        maps.put("3", "3");
        boolean isWrite=false;
        if(isWrite){
            redismapService.set("maps", maps);
        }else
        {
            Map<String, String> map = redismapService.get("maps");
            System.out.println("RedisTest.testMapObject():"+map.toString());
        }
    }

}
