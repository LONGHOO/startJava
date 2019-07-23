package cn.wolfcode.trip.base.Storage;

/**
 * @Author: 十一
 * @Date: 2019-05-20 15:03
 * @Descrption 自定义缩略图模式
 **/
public enum  ThumbnailModel {

    THUMB_16("imageView2/2/w/16/h/16"),
    THUMB_32("imageView2/2/w/32/h/32"),
    THUMB_64("imageView2/2/w/64/h/64"),
    THUMB_128("imageView2/2/w/128/h/128"),
    THUMB_256("imageView2/2/w/256/h/256"),
    THUMB_512("imageView2/2/w/512/h/512"),
    THUMB_1024("imageView2/2/w/1024/h/1024");
    private String value = "";
    private ThumbnailModel(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
