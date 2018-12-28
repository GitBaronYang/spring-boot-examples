package com.yang.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;

/**
 * @Author root
 * @Date 2018/4/18
 * @Describe
 */

@Controller
public class FileUploadController {

    @Value("${upload.store.dir}")
    private String UPLOAD_STORE_PATH;
    /**
     *
     */
    @Value("${upload.tmp.dir}")
    private String UPLOAD_TMP_PATH;

    @RequestMapping("/index")
    public String provideUploadInfo(Model model) {
        return "index";
    }
    @RequestMapping("/upload")
    public String upload(Model model) throws IOException{
        Path dir = Paths.get(UPLOAD_TMP_PATH);
        if(!Files.exists(dir)){
            Files.createDirectory(dir);
        }
        dir = Paths.get(UPLOAD_STORE_PATH);
        if(!Files.exists(dir)){
            Files.createDirectory(dir);
        }
        return "upload";
    }

    /**
     *
     * @param name
     * @param file
     * @param chunks
     * @param chunk
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(
            @RequestParam("name") String name,
            @RequestParam("file") MultipartFile file,
            @RequestParam(required=false, defaultValue="-1") int chunks,
            @RequestParam(required=false, defaultValue="-1") int chunk) throws IOException {

        Path source = Paths.get(UPLOAD_TMP_PATH,name);
        //create temp file
        //first call
        if(chunk==0 && chunks>=0) {
            Files.deleteIfExists(source);
            Files.createFile(source);
            Files.write(source, file.getBytes(), StandardOpenOption.APPEND);
        }
        if (chunks > 0 && chunk > 0) {
            Files.write(source, file.getBytes(), StandardOpenOption.APPEND);
            //last call
            if (chunk == chunks - 1) {
                Files.write(source, file.getBytes(), StandardOpenOption.APPEND);
                Path target = Paths.get(UPLOAD_STORE_PATH, name);
                Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
                return target.toString();
            }
        }
        return "ok";
    }

//    @ResponseBody
//    @RequestMapping(value="/pluploadUpload",method = RequestMethod.POST)
//    public void upload(Plupload plupload, HttpServletRequest request, HttpServletResponse response){
//        String FileDir = "pluploadDir";//文件保存的文件夹
//        plupload.setRequest(request);//手动传入Plupload对象HttpServletRequest属性
//
//        //文件存储绝对路径,会是一个文件夹，项目相应Servlet容器下的"pluploadDir"文件夹，还会以用户唯一id作划分
//        File dir = new File(request.getSession().getServletContext().getRealPath("/") + FileDir+"/"+"hello");
//        if(!dir.exists()){
//            dir.mkdirs();//可创建多级目录，而mkdir()只能创建一级目录
//        }
//        //开始上传文件
//        PluploadService.upload(plupload, dir);
//    }
}
