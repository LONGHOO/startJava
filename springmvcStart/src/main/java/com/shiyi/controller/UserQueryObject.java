package com.shiyi.controller;

/**
 * @Author: 十一
 * @Date: 2019-04-17 20:26
 * @Descrption
 **/
public class UserQueryObject {

    private String keyWord;
    private Integer maxAge;
    private Integer minAge;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    @Override
    public String toString() {
        return "UserQueryObject{" +
                "keyWord='" + keyWord + '\'' +
                ", maxAge=" + maxAge +
                ", minAge=" + minAge +
                '}';
    }
}
