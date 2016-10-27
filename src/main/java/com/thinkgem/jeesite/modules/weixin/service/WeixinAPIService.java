package com.thinkgem.jeesite.modules.weixin.service;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.modules.sys.utils.LogUtils;
import com.thinkgem.jeesite.modules.weixin.vo.AccessTokenRep;
import com.thinkgem.jeesite.modules.weixin.vo.GetOpenIdRep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by lala on 16/10/27.
 */

@Component
public class WeixinAPIService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value("#{APP_PROP['config.weixin.access_token.url']}")
    private String ACCESS_TOKEN_URL;

    @Value("#{APP_PROP['config.weixin.openId.url']}")
    String REQUEST_OPEN_ID_URL;

    @Value("#{APP_PROP['config.weixin.appId']}")
    private String APP_ID;

    @Value("#{APP_PROP['config.weixin.appSecret']}")
    private String APP_SECRET;

    @Value("#{APP_PROP['config.weixin.openId.url_returnurl']}")
    private String REQUEST_OPEN_ID_RETURN_URL;


    @Value("#{APP_PROP['config.weixin.openId.request.url']}")
    private String REQUEST_OPEN_ID_REQUEST_URL;




    public String getOrNewToken() {
        String access_token = "-1";
        access_token = (String) CacheUtils.get("weixinAccessTokenCache","access_token");
        if(access_token==null||access_token.equals("")){
            //todo 重新获取access_token
            RestTemplate restTemplate = new RestTemplate();
            logger.debug("ready retrieve access_token from url "+ACCESS_TOKEN_URL);
            String rep = restTemplate.getForObject(ACCESS_TOKEN_URL+"&appid={appid}&secret={appsecret}", String.class,APP_ID,APP_SECRET);
            if(rep.contains("{\"access_token")){
                AccessTokenRep accessTokenRep = JsonMapper.getInstance().fromJson(rep,AccessTokenRep.class);
                access_token = accessTokenRep.getAccessToken();
                CacheUtils.put("weixinAccessTokenCache","access_token",access_token);

            }
            logger.debug("accessTokenRep="+rep);
        }

        return access_token;
    }


    public String getRequestOpenIDUrl(){
        try {
            String url = REQUEST_OPEN_ID_URL+"?appid="+APP_ID+"&redirect_uri="+ URLEncoder.encode(REQUEST_OPEN_ID_RETURN_URL,"utf-8")+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
            logger.debug("url="+url);

            return url;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "-1";
    }


    public String getOpenIdByCode(String code){
        logger.debug("mthod getOpenIdByCode() called.");
        try {
            String url = REQUEST_OPEN_ID_REQUEST_URL+"?appid={appid}&secret={secret}&code={code}&grant_type=authorization_code";
            logger.debug("url="+url);
            RestTemplate restTemplate = new RestTemplate();

            String rep = restTemplate.getForObject(url, String.class,APP_ID,APP_SECRET,code);
            logger.debug("rep="+rep);
            if(rep.contains("{\"access_token")){
                GetOpenIdRep accessTokenRep = JsonMapper.getInstance().fromJson(rep,GetOpenIdRep.class);
            }

            return rep;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }
}
