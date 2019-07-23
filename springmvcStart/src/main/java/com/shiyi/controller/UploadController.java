package com.shiyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: 十一
 * @Date: 2019-04-17 20:25
 * @Descrption
 **/
@Controller
public class UploadController {

    @Autowired
    private ServletContext context;

    @RequestMapping("/upload")
    public void upload(MultipartFile file) throws IOException {
        String realPath = context.getRealPath("/uploadfile");
        System.out.println(realPath);
        System.out.println(file.getOriginalFilename());
        FileCopyUtils.copy(file.getInputStream(),
                new FileOutputStream(realPath + "/" + file.getOriginalFilename()));
    }
}
