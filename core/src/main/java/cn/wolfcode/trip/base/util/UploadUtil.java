package cn.wolfcode.trip.base.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传工具
 */
public class UploadUtil {

	public static final String IMAGE_PATH = "D:/OpenSources/tomcat-image/webapps/ROOT";
	public static final String IMAGE_DOMAIN = "http://localhost:5555/";
	public static final String IMAGE_QINIU_DOMAIN = "http://prqv8shgy.bkt.clouddn.com/";

	/**
	 * 处理文件上传
	 *
	 * @param file
	 * @return 123.png
	 */
	public static String upload(MultipartFile file) {
        //随机字符串
		String uuid = UUID.randomUUID().toString();
        //获取文件名称 xxxx.jpg
		String orgFileName = file.getOriginalFilename();
		String ext = "." + FilenameUtils.getExtension(orgFileName);
		String fileName = uuid + ext;
		try {
			File targetFile = new File(IMAGE_PATH, fileName);
            //写入文件数据
			FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
            //返回文件的http地址
			return IMAGE_DOMAIN + fileName;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 上传到七牛云
	 * @param file
	 * @return
	 */
	public static String uploadQiniu(MultipartFile file) {
		//构造一个带指定Zone对象的配置类
        //机房区域
		Configuration cfg = new Configuration(Zone.zone2());
		//...其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		//...生成上传凭证，然后准备上传
		String accessKey = "m-Axoy2_YdF38ZIOwE9MXHJwwy_CFmt3om_755_O";
		String secretKey = "dXjGhWPXI8WWb42ehwoRVs20NWh7azSoeMke1r_A";
        //存储空间
		String bucket = "wowo";
		//默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = null;
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		try {
			//上传到服务器
			Response response = uploadManager.put(file.getBytes(), key, upToken);
			//解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			//返回文件名
			return IMAGE_QINIU_DOMAIN+putRet.key;
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				//ignore
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
























