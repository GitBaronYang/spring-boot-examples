package com.yang.basicmodel.entity;

import java.util.Arrays;
import java.util.List;

import com.yang.basicmodel.entity.test.Student;

public class Message {
	private String message;
	
	private int[] nums;

	private List<Integer> its;
	
	private Student student;
	
	private List<Student> students;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int[] getNums() {
        return nums;
    }

    public void setNums(int[] nums) {
        this.nums = nums;
    }

    public List<Integer> getIts() {
        return its;
    }

    public void setIts(List<Integer> its) {
        this.its = its;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Message [message=" + message + ", nums=" + Arrays.toString(nums) + ", its=" + its + ", student="
                + student + ", students=" + students + "]";
    }
	
}
