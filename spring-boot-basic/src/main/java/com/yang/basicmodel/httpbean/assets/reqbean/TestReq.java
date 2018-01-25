package com.yang.basicmodel.httpbean.assets.reqbean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/** 
* @author yf
* 资料：http://blog.csdn.net/d292222100/article/details/77867403
* 使用@Valid注解进行参数检查
* 在没有使用BindingResult时，程序会抛出空指针等异常信息，不能返回自定义信息
* @NotNull，@Pattern，@Size，@Max，@Min，@Digits是我在项目中经常使用的注解
* @date 创建时间：2017年12月1日 下午12:12:29 
* @version 1.0   
*/
public class TestReq {
    
    @Min(value = 6, message = "id不能小于6")
    private int id;
    
    @NotNull(message = "字段不允许为空")
    private String testName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    public String toString() {
        return "TestReq [id=" + id + ", testName=" + testName + "]";
    }
}
