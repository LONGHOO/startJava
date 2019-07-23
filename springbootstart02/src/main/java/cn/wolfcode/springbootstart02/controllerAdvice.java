package cn.wolfcode.springbootstart02;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: 十一
 * @Date: 2019-06-10 16:51
 * @Descrption
 **/
@ControllerAdvice
public class controllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public String handler(Model model,Exception e){
        model.addAttribute("errorMsg",e.getMessage());
        return "error";
    }
}
