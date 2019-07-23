package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.Storage.QiniuStorage;
import cn.wolfcode.trip.base.Storage.ThumbnailModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 十一
 * @Date: 2019-05-19 19:00
 * @Descrption
 **/
@RestController
@RequestMapping("images")
public class ImageController {

    @RequestMapping("upload")
    public Map upload(MultipartFile upload){
        Map<String,Object> map = new HashMap();
        try{
            String url = QiniuStorage.getUrlByThumb(QiniuStorage.uploadImage(upload.getBytes()),
                    ThumbnailModel.THUMB_512);
            map.put("uploaded", 1);
            map.put("url",url);
        }catch(Exception e){
            e.printStackTrace();
            map.put("uploaded", 0);
            Map<String,Object> subMap = new HashMap();
            subMap.put("message", e.getMessage());
            map.put("error", subMap);
        }
        return map;
    }
}
