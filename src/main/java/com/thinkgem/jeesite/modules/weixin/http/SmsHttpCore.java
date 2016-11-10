package com.thinkgem.jeesite.modules.weixin.http;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信接口
 * Created by lala on 16/11/9.
 */
@Component
public class SmsHttpCore {
    @Autowired
    RestTemplate restTemplate ;

    @Value("#{APP_PROP['config.sys.sms.url']}")
    String smsUrl;

    public void send(String mobile){

        int code=1 + (int)(Math.random() * ((999999 - 1) + 1));
        CacheUtils.put(CacheUtils.Weixin_CACHE_VERIFY_CODE,mobile,code);
        String rep = restTemplate.getForObject(smsUrl+"?account=jk_cs_10&pswd=Bbu123666&mobile="+mobile+"&msg=[iGoach]您正在使用登录服务,验证码:"+code+".请勿转发&needstatus=false", String.class);
    }

}
