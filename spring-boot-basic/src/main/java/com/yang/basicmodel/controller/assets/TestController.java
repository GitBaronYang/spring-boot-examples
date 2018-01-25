package com.yang.basicmodel.controller.assets;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yang.basicmodel.ReturnCode;
import com.yang.basicmodel.httpbean.assets.reqbean.TestReq;
import com.yang.basicmodel.service.assets.TestService;




/** 
* @author  
* @E-mail: 
* @date 创建时间：2017年12月1日 下午2:17:21 
* @version 1.0   
*/
@RestController
@RequestMapping("/Test")
public class TestController {
    
    private final static Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;
    /**
     * @功能描述
     * @param 通用注解校验 @Valid
     * @return
     * @param testReq
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/testValid", method = RequestMethod.POST)
    public ReturnCode testValid(@RequestBody @Valid TestReq testReq,BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
             String msg = bindingResult.getFieldError().getDefaultMessage();
             return new ReturnCode.Builder().code(-1).msg(msg).build();
        }
        ReturnCode code = new ReturnCode.Builder().code(1).msg("成功").build();
        return code;
    }
    
    
    /**
     * @功能描述  如果入参校验过于复杂使用@RequestBody ，校验规则自己写
     * @param
     * @return
     * @param testReq
     * @return
     */
    @PostMapping(value = "/testCommon")
    public ReturnCode testCommon(@RequestBody TestReq testReq) {
        //参数校验
        //pass
        //测试日志分级别打印到文件
        log.error("error test");
        log.info("info test");
        log.warn("warn test");
        return new ReturnCode.Builder().code(1).msg("成功").build();
    }
    
    @GetMapping(value = "/testException")
    public ReturnCode testException() {
        ReturnCode code = null;
        testService.TestException();
        return code;
    }
    
    
    @GetMapping(value = "/testCreateDir")
    public ReturnCode testCreateDir(HttpServletRequest request) {
        ReturnCode code = null;
        testService.testCreateDir(request);
        return code;
    }
}
