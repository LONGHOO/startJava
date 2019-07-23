package com;

import com.birthdayReminder.days.BirthdaysContans;
import com.com.birthdayReminder.utils.LunarSolarConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Calendar;

public class BrithdayMain {

    @Autowired
    private JavaMailSender mailSender;

    public void checkOutBirthday(){
        Calendar calendar = Calendar.getInstance();
        for(int i = 0;i <= 3;i++){
            calendar.add(Calendar.DATE,i);
            LunarSolarConverter.Lunar lunar = LunarSolarConverter.converterDate(calendar.getTimeInMillis());
            for (int j = 0; j < BirthdaysContans.BIRTHLIST.length; j++) {
                if((lunar.getMonth()+"月"+lunar.getDay()).equals(BirthdaysContans.BIRTHLIST[j])){
                    this.sendMail(i,j);
                }
            }
        }
        LunarSolarConverter.Lunar lunar = LunarSolarConverter.converterDate(calendar.getTimeInMillis());
        System.out.println(lunar.getMonth()+"月"+lunar.getDay());
    }

    public void sendMail(int days,int index){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1144258019@qq.com");
        for(String mail : BirthdaysContans.MAILLIST){
            message.setTo(mail);
            message.setSubject("主题："+BirthdaysContans.BIRTHNAMES[index]+"的生日提醒");
            String messageContent = null;
            if(days == 0){
                messageContent = "今天是"+BirthdaysContans.BIRTHNAMES[index]+"的生日，送上生日祝福吧！";
            }else{
                messageContent = days+"后是"+BirthdaysContans.BIRTHNAMES[index]+"的生日，送上生日祝福吧！";
            }
            message.setText(messageContent);
            mailSender.send(message);
        }
    }

}
