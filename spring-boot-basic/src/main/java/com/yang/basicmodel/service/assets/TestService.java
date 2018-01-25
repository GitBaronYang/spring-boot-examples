package com.yang.basicmodel.service.assets;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yang.basicmodel.enums.ResultEnum;
import com.yang.basicmodel.exception.CustomException;

@Service
public class TestService {
    
    private final static Logger log = LoggerFactory.getLogger(TestService.class);
    
    private String templateUrl="Headfiles";
    
    /**
     * @功能描述 自定义异常
     * @param
     * @return
     */
    public void TestException(){
        Boolean error = true;
        if (error) {
            throw new CustomException(ResultEnum.CUSTOM_ERROR);
        }
    }
    
    public void testCreateDir(HttpServletRequest request){
     // 获取文件名
        File basepath = new File(request.getSession().getServletContext().getRealPath("/"));
        log.error("1---->"+basepath.toString());
        String path = "";
        //判断当前操作系统
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            path = basepath.getParent()+"/"+templateUrl;
            log.error("2---->"+path);
        }else{
            path = basepath.getParent() +"/"+templateUrl;
            log.error("3---->"+path);
        }
        saveResource(path);
    }
    
    public void saveResource(String path) {
        if (!path.isEmpty()) {
            try {
                File fileDir = new File(path);
                if(!fileDir.exists()) {
                    fileDir.mkdirs();
                }
            } catch(Exception e) {
                System.out.println("TestService.saveResource()"+e.getStackTrace()[0].toString());
            }
        }
    }
}
