package com.shiyi.springbootmybatisdemo.start;

/**
 * @Author: 十一
 * @Date: 2019-03-11 17:31
 * @Descrption
 **/
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private Long id;
    private String username;
    private Integer age;

}
