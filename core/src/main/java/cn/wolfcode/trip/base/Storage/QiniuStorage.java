package cn.wolfcode.trip.base.Storage;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author: 十一
 * @Date: 2019-05-20 17:34
 * @Descrption
 **/
public class QiniuStorage {

    /**
     *
     * 功能描述:  图片上传
     * @params:  图片的字节码数组
     * @return:  图片的key
     * @auther: 十一
     * @date:   2019-05-20 17:38
     */
    public static String uploadImage(byte[] buff){
       if(FileTypeHelper.isImage(buff)){
           String key = QiniuKeyGenerator.generateKey();
           return  QiniuAction.upload(buff, key);
       }
       return null;
    }

    /**
     *
     * 功能描述:  图片批量上传上传
     * @params:  图片的字节码数组的数组
     * @return:  图片的key的集合
     * @auther: 十一
     * @date:   2019-05-20 17:48
     */
    public static List uploadImages(byte[][] buff){
        List<String> list = new ArrayList<>();
        for (byte[] bytes : buff) {
            if(FileTypeHelper.isImage(bytes)){
                String key = QiniuKeyGenerator.generateKey();
                list.add(QiniuAction.upload(bytes, key));
            }
        }
        return list;
    }

    /**
     *
     * 功能描述: 获取长期有效的url
     * @params:  key
     * @return:
     * @auther: 十一
     * @date:   2019-05-20 17:43
     */
    public static String getUrl(String key){
        return QiniuAction.getUrl(key);
    }

    /**
     *
     * 功能描述: 获取经过缩放的图片的有效url
     * @params:  图片key，缩放对象
     * @return:
     * @auther: 十一
     * @date:   2019-05-20 17:44
     */
    public static String getUrlByThumb(String key,ThumbnailModel model){
        return QiniuAction.getUrlByThumb(key,model.getValue());
    }

    /**
     *
     * 功能描述: 获取图片的临时url地址，1小时后过期
     * @params:  图片的key
     * @return:
     * @auther: 十一
     * @date:   2019-05-20 17:46
     */
    public static String getPrivateUrl(String key){
        return QiniuAction.getPrivateUrl(key);
    }

    /**
     * 功能描述: 获取图片被缩放的临时url地址，1小时后过期
     * @params:  图片的key，缩放类型
     * @return:
     * @auther: 十一
     * @date:   2019-05-20 17:46
     */
    public static String getPrivateUrl(String key,ThumbnailModel model){
        return QiniuAction.getPrivateUrl(key,model.getValue());
    }

    /**
     *
     * 功能描述: 获取图片的按比例缩放临时url地址，自定义过期时间，单位秒
     * @params:  图片的key，缩放类型，过期时间
     * @return:
     * @auther: 十一
     * @date:   2019-05-20 17:46
     */
    public static String getPrivateUrl(String key,ThumbnailModel model,Long expire){
        return QiniuAction.getPrivateUrl(key,model.getValue(),expire);
    }


    /**
     *
     * 功能描述: 上传图片并获取图片的地址
     * @params:  图片的字节数组
     * @return:
     * @auther: 十一
     *
     */
    public static String getUploadUrl(byte[] buff){
        String key = uploadImage(buff);
        return getUrl(key);
    }

}
