package cn.wolfcode.trip.base.Storage;

import java.util.Arrays;

/**
 * @Author: 十一
 * @Date: 2019-05-20 14:28
 * @Descrption 判断传入的字节码数组是否是图片
 **/
public class FileTypeHelper {

    /**
     *
     * 功能描述:  如果传入的字节流是JPEG，PNG，GIF，则返回true；否则返回false
     * @params:  图片字节流
     * @return:  true，false
     * @auther: 十一
     * @date:    14:54
     */
    public static boolean isImage(byte[] bytes){
        FileType type = getType(bytes);
        if(FileType.GIF.equals(type) || FileType.PNG.equals(type)|| FileType.JPEG.equals(type)){
            return true;
        }
        return false;
    }

    /**
     *
     * 功能描述:  返回自己流是否是图片
     * @params:  文件的字节流
     * @return:  文件类型
     * @auther: 十一
     * @date:   2019-05-20 14:52
     */
    public static FileType getType(byte[] bytes){
        //如果字节数组小于28抛出异常
        if(bytes.length < 28){
           throw new RuntimeException("图片太小!");
        }
        byte[] head = Arrays.copyOfRange(bytes, 0, 28);
        String headType = byteToHex(head);
        for (FileType type : FileType.values()) {
            if(headType.startsWith(type.getValue())){
                return type;
            }
        }
        return null;
    }

    /**
     *
     * 功能描述:
     * @params:  需要校验的图片头
     * @return:  图片头的十六进制字符串
     * @auther: 十一
     * @date:   2019-05-20 14:51
     */
    public static String byteToHex(byte[] bytes){
        if(bytes == null || bytes.length <= 0){
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes) {
            //将字节转换成二进制
            int bin = b & 0xff;
            //将二进制装换成十六进制的字符串
            String hex = Integer.toHexString(bin);
            //如果生成的十六进制小于等于f，则头部填充0
            if(hex.length() < 2){
                sb.append(0);
            }
            sb.append(hex);
        }
        return sb.toString().toUpperCase();
    }
}
