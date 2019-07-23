package cn.wolfcode.trip.api.controller;

import cn.wolfcode.trip.base.Storage.QiniuStorage;
import cn.wolfcode.trip.base.Storage.ThumbnailModel;
import cn.wolfcode.trip.base.util.UploadUtil;
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
@RequestMapping("image")
public class ImageController {

    @RequestMapping("upload")
    public Map upload(MultipartFile file){
        Map<String,Object> map = new HashMap();
        try{
//            String url = UploadUtil.uploadQiniu(file);
            String url = QiniuStorage.getUrlByThumb(QiniuStorage.uploadImage(file.getBytes()),
                    ThumbnailModel.THUMB_512);
            map.put("status", 1);
            map.put("url",url);
        }catch(Exception e){
            e.printStackTrace();
            map.put("status", 0);
            map.put("msg", e.getMessage());
        }
        return map;
    }
}
