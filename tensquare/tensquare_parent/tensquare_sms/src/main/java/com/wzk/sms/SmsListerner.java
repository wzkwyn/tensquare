package com.wzk.sms;

import com.aliyuncs.exceptions.ClientException;
import com.wzk.sms.util.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class SmsListerner {
    @Autowired
    private SmsUtil smsUtil;
    @RabbitHandler
    public void sendCode(Map<String,String> messageMap) {
        String mobile = messageMap.get("mobile");
        String code = messageMap.get("code");
        System.out.println("手机号："+mobile+"  ,验证码："+code);
        try {
            smsUtil.sendSms(mobile,"模板id","签名","{\"code\":"+ code +"}");
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
