package com.thinkgem.jeesite.modules.weixin.http;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.weixin.vo.WeixinTemplateMsg;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lala on 16/11/1.
 */
@Component
public class WeixinHttpCore {
    @Autowired
    RestTemplate restTemplate ;
//    RestTemplate restTemplate = new RestTemplate();

    @Value("#{APP_PROP['config.weixin.custom']}")
    String weixinCustomHost;

    @Value("#{APP_PROP['config.weixin.menu']}")
    String weixinMenuHost;

    @Value("#{APP_PROP['config.weixin.root.url']}")
    String weixinRootHost;

    /**
     * 获取客服人员信息列表
     * @param accessToken
     * @return
     */
    public String getkfList(String accessToken){
        String url = weixinCustomHost+"/kfaccount/getkflist?access_token="+accessToken;
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 获取客服人员在线状态
     * @param accessToken
     * @return
     */
    public String getOnlinekfList(String accessToken){
        String url = weixinCustomHost+"/kfaccount/getonlinekflist?access_token="+accessToken;
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 创建会话
     * @param accessToken
     * @param kfAccount
     * @param openId
     * @return
     */
    public String createKfSession(String accessToken,String kfAccount,String openId){
        String url = weixinCustomHost+"/kfsession/create?access_token="+accessToken;
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String data = "{\"kf_account\" : \""+kfAccount+"\",\"openid\" : \""+openId+"\",\"text\" : \"你好\"}";
        HttpEntity<String> formEntity = new HttpEntity<String>(data, headers);
        String rep = restTemplate.postForObject(url, formEntity, String.class);
        return rep;
    }

    /**
     * 关闭会话
     * @param accessToken
     * @param kfAccount
     * @param openId
     * @return
     */
    public String closeKfSession(String accessToken,String kfAccount,String openId){
        String url = weixinCustomHost+"/kfsession/close?access_token="+accessToken;
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String data = "{\"kf_account\" : \""+kfAccount+"\",\"openid\" : \""+openId+"\",\"text\" : \"再见\"}";
        HttpEntity<String> formEntity = new HttpEntity<String>(data, headers);
        String rep = restTemplate.postForObject(url, formEntity, String.class);
        return rep;
    }


    /**
     * 获取客户的会话状态
     * @param accessToken
     * @param openId
     * @return
     */
    public String getKfSession(String accessToken,String openId){
        String url = weixinCustomHost+"/kfsession/getsession?access_token="+accessToken+"&openid="+openId;
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 获取客服的会话列表
     * @param accessToken
     * @param kfAccount
     * @return
     *
     *
     */
    public String getKfSessionList(String accessToken,String kfAccount){
        /*{
            "sessionlist" : [
            {
                "createtime" : 123456789,
                    "openid" : "OPENID"
            },
            {
                "createtime" : 123456789,
                    "openid" : "OPENID"
            }
            ]
        }*/

        String url = weixinCustomHost+"/kfsession/getsessionlist?access_token="+accessToken+"&kf_account="+kfAccount;
        return restTemplate.getForObject(url, String.class);
    }


    /**
     * 获取未接入会话列表
     * @param accessToken
     * @return
     *
     *
     */
    public String getKfSessionList(String accessToken){
        /*  {
    "count" : 150,
    "waitcaselist" : [
       {
          "createtime" : 123456789,
          "kf_account" : "test1@test",
          "openid" : "OPENID"
       },
       {
          "createtime" : 123456789,
          "kf_account" : "",
          "openid" : "OPENID"
       }
    ]
 }*/

        String url = weixinCustomHost+"/kfsession/getwaitcase?access_token="+accessToken;
        return restTemplate.getForObject(url, String.class);
    }


    /**
     * 获取客服聊天记录接口
     * @param accessToken
     * @param startTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public String getCustomMsgRecord(String accessToken,String startTime,String endTime,int pageIndex,int pageSize){

        /*{
            "errcode": 0,
                "errmsg": "",
                "retcode": 0,
                "recordlist" : [
            {
                "openid" : "oDF3iY9WMaswOPWjCIp_f3Bnpljk",
                    "opercode" : 2002,
                    "text" : " 您好，客服test1为您服务。",
                    "time" : 1400563710,
                    "worker" : "test1"
            },
            {
                "openid" : "oDF3iY9WMaswOPWjCIp_f3Bnpljk",
                    "opercode" : 2003,
                    "text" : "你好，有什么事情？",
                    "time" : 1400563731,
                    "worker" : "test1"
            }
            ]
        }*/

        String url = weixinCustomHost+"/msgrecord/getrecord?access_token="+accessToken;
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String data = " {\"endtime\" : "+endTime+",\"pageindex\" : "+pageIndex+",\"pagesize\" : "+pageSize+",\"starttime\" : "+startTime+"}";
        HttpEntity<String> formEntity = new HttpEntity<String>(data, headers);
        String rep = restTemplate.postForObject(url, formEntity, String.class);
        return rep;
    }

    /**
     * 创建菜单
     * @param accessToken
     * @param menus
     * @return
     */
    public String menuCreate(String accessToken,String menus){
        String url = weixinMenuHost+"/create?access_token="+accessToken;
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(menus, headers);
        String rep = restTemplate.postForObject(url, formEntity, String.class);
        return rep;
    }

    /**
     * 删除菜单
     * @param accessToken
     * @return
     *
     *
     */
    public String deleteMenu(String accessToken){
        String url = weixinMenuHost+"/delete?access_token="+accessToken;
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 获取菜单列表
     * @param accessToken
     * @return
     *
     *
     */
    public String getMenu(String accessToken){
        String url = weixinRootHost+"/menu/get?access_token="+accessToken;
        return restTemplate.getForObject(url, String.class);
    }


    /**
     * 获取模板列表
     * @param accessToken
     * @return
     *
     *
     */
    public String getAllPrivateTemplate(String accessToken){
        String url = weixinRootHost+"/template/get_all_private_template?access_token="+accessToken;
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 获取用户基本信息
     * @param accessToken
     * @return
     *
     *
     */
    public String getUserInfoByOpenId(String openId,String accessToken){
        String url = weixinRootHost+"/user/info?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 发送模板消息
     * @param accessToken
     * @return
     */
    public String sendTemplateMsg(String accessToken,WeixinTemplateMsg weixinTemplateMsg){
        String url = weixinRootHost+"message/template/send?access_token="+accessToken;
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(JsonMapper.toJsonString(weixinTemplateMsg), headers);
        String rep = restTemplate.postForObject(url, formEntity, String.class);
        return rep;
    }

}
