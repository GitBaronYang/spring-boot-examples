package com.yang.basicmodel.entity.test;

public class Student {

    private String cls;
    private int age;
    public String getCls() {
        return cls;
    }
    public void setCls(String cls) {
        this.cls = cls;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Student [cls=" + cls + ", age=" + age + "]";
    }
    
}
