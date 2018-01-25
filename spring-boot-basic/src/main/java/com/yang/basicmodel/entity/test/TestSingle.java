package com.yang.basicmodel.entity.test;

import com.yang.basicmodel.entity.Message;

/** 
* @author  test redis
* @E-mail: 
* @date 创建时间：2018年1月24日 上午9:25:30 
* @version 1.0   
*/
public class TestSingle {
    private String name;
    private String hobby;
    private String address;
    private int age;
    private Message message;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getHobby() {
        return hobby;
    }
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "TestSingle [name=" + name + ", hobby=" + hobby + ", address=" + address + ", age=" + age + ", message="
                + message + "]";
    }
}
