package cn.wolfcode.trip.base.Storage;

import cn.wolfcode.trip.base.util.StringUtils;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author: 十一
 * @Date: 2019-05-20 15:52
 * @Descrption
 **/
public class QiniuAction {

    private static final Logger logger = LoggerFactory.getLogger(QiniuAction.class);

    private static final String CONFIG_BUCKET="qiniu.bucket";
    private static final String CONFIG_AK="qiniu.accesskey";
    private static final String CONFIG_SK="qiniu.secretkey";
    private static final String CONFIG_CDN="qiniu.cdns";
    private static UploadManager uploadManager;
    private static Auth auth;

    private static String BUCKET;
    private static String CDN;
    static{
        Properties properties = new Properties();
        try{
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("qiniu.properties");
            properties.load(is);
            auth = Auth.create(properties.getProperty(CONFIG_AK), properties.getProperty(CONFIG_SK));
            Configuration conf = new Configuration(Zone.zone2());
            uploadManager = new UploadManager(conf);
            BUCKET = properties.getProperty(CONFIG_BUCKET);
            CDN = properties.getProperty(CONFIG_CDN);
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    /**
     *
     * 功能描述:
     * @params:  无
     * @return:  简单的上传凭证
     * @auther: 十一
     * @date:   2019-05-20 16:10
     */
    public static String getUploadToken(){
        return auth.uploadToken(BUCKET);
    }
    
    /**
     *
     * 功能描述:
     * @params:  自定义的keyname
     * @return:  覆盖上传的凭证
     * @auther: 十一
     * @date:   2019-05-20 16:11
     */
    public static String getUploadToken(String key){
        return auth.uploadToken(BUCKET,key);
    }

    /**
     *
     * 功能描述:  图片的上传操作
     * @params:  图片字节流
     * @return:  key，可为空，表示使用图片的hash码作为文件名
     * @auther: 十一
     * @date:   2019-05-20 16:27
     */
    public static String upload(byte[] data,String key){
        String token = key == null ? getUploadToken(key) : getUploadToken();
        try {
            Response response = uploadManager.put(data, getFullKey(data, key), token);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return BUCKET+"/"+ putRet.key;
        } catch (QiniuException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     *
     * 功能描述:
     * @params:  图片字节数组，key
     * @return:  包含扩展名的自定义key
     * @auther: 十一
     * @date:   2019-05-20 16:24
     */
    private static String getFullKey(byte[] data, String key) {
        if(key == null){
            return null;
        }
        FileType type = FileTypeHelper.getType(data);
        String newType = key+"."+type.name().toLowerCase();
        return newType;
    }

    /**
     *
     * 功能描述: 获取公开链接的原图
     * @params: 图片的key
     * @return: 公开链接
     * @auther: 十一
     * @date:   2019-05-20 16:31
     */
    public static String getUrl(String key){
        if(StringUtils.hasLength(key)){
            return CDN+"/"+key;
        }
        return null;
    }

    /**
     *
     * 功能描述: 获取公开链接，并对图片进行裁剪
     * @params: 图片的key
     * @return: url
     * @auther: 十一
     * @date:   2019-05-20 16:32
     */
    public static String getUrlByThumb(String key,String model){
        if(StringUtils.hasLength(key) && StringUtils.hasLength(model)){
            return CDN + "/" + key+"?"+model;
        }
        return null;
    }

    /**
     *
     * 功能描述: 返回一小时后过期的url地址
     * @params: 图片的key
     * @return: 私有链接，会过期，默认一小时后过期
     * @auther: 十一
     * @date:   2019-05-20 16:56
     */
    public static String getPrivateUrl(String key){
        if(StringUtils.hasLength(key)){
            long expireInSecondes = 30L;
            long time = System.currentTimeMillis()/1000+expireInSecondes;
            return auth.privateDownloadUrl(CDN+"/"+key,time);
        }
        return null;
    }

    /**
     *
     * 功能描述: 返回进行等比例缩放的url，过期时间为1小时
     * @params:  图片的key，缩放比例
     * @return:  url
     * @auther: 十一
     * @date:   2019-05-20 17:17
     */
    public static String getPrivateUrl(String key,String model){
        if(StringUtils.hasLength(key) && StringUtils.hasLength(model)){
            return getPrivateUrl(key+"?"+model);
        }
        return null;
    }

    /**
     *
     * 功能描述: 获取自定义的压缩方式和过期时间
     * @params:  key,model,time
     * @return:
     * @auther: 十一
     * @date:   2019-05-20 17:22
     */
    public static String getPrivateUrl(String key,String model,Long time){
        if(StringUtils.hasLength(key)){
            if(time == null){
                return getPrivateUrl(key,model);
            }else if(model == null){
                return getPrivateUrl(key);
            }else{
                return auth.privateDownloadUrl(CDN+"/"+key+"?"+model,time);
            }
        }
        return null;
    }

    /**
     *
     * 功能描述: 获取自定义的压缩方式和过期时间
     * @params:  key,model,time
     * @return:
     * @auther: 十一
     * @date:   2019-05-20 17:32
     */
    public static List getUrls(String keys, String model){
        List<String> list = null;
        if (org.springframework.util.StringUtils.hasLength(keys)) {
            list = new ArrayList();
            for (String key : keys.split(",")) {
                if(StringUtils.hasLength(model)){
                    list.add(getUrlByThumb(key,model));
                }else{
                    list.add(getUrl(key));
                }
            }
        }
        return list;
    }
}
