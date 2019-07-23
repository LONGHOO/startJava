package cn.wolfcode.p2p.controller;

import cn.wolfcode.p2p.util.UploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 十一
 * @Date: 2019-06-18 18:45
 * @Descrption
 **/
@Controller
public class UploadImgController {

    @Value("${file.uploadPath}")
    private String basepath;

    @RequestMapping("uploadImg")
    @ResponseBody
    public String uploadImg(MultipartFile pic){
        String name = UploadUtil.upload(pic, basepath);
        return name;
    }
}
