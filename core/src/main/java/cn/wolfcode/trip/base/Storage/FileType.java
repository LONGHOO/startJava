package cn.wolfcode.trip.base.Storage;

/**
 * @Author: 十一
 * @Date: 2019-05-20 11:58
 * @Descrption JPEG,PNG,GIF三种图片文件二进制文件开头的十六进值
 **/
public enum FileType {
    JPEG("FFD8FF"),
    PNG("89504E47"),
    GIF("47494638");

    private String value = "";

    private FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }}
