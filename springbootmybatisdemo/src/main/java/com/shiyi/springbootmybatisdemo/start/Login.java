package com.shiyi.springbootmybatisdemo.start;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * @Author: 十一
 * @Date: 2019-03-13 21:34
 * @Descrption
 **/
@Controller
@RequestMapping("/login")
public class Login {

    @RequestMapping("/code")
    public void getVerifiCode(HttpServletRequest request,HttpServletResponse res){
        char[] code = this.createVerifyCode();
        int width = 150;
        int height = 100;
        BufferedImage img = new BufferedImage(width, height, TYPE_INT_RGB);
        Graphics graphics = img.getGraphics();
        Font font = new Font("Fixedsys", Font.BOLD, 40);
        graphics.setColor(Color.white);
        graphics.setFont(font);
        graphics.fillRect(0,0,width,height);
        graphics.setColor(Color.BLACK);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < code.length; i++) {
            graphics.drawString(code[i]+"",i*30+20,60);
            sb.append(code[i]);
        }
        request.getSession().setAttribute("verifyCode",sb.toString());
        try {
            ImageIO.write(img,"jpg",res.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping("/login")
    public String login(HttpServletRequest request,HttpServletResponse res){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verifyCode = request.getParameter("verifyCode");
        System.out.println(verifyCode);
        String code =(String) request.getSession().getAttribute("verifyCode");
        System.out.println(code);
        if(code.equals(verifyCode)){
            return "success";
        }
        return "fail";
    }

    public char[] createVerifyCode(){
        char[] chars = new char[]{'1','2','3','4','5','6','7','8','9','0','q','w','e','r','t','y',
                'u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'};
        char[] verifyCodeChar = new char[4];
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<4;i++){
            char c = chars[new Random().nextInt(chars.length)];
            verifyCodeChar[i] = c;
        }
        return verifyCodeChar;
    }
}
