package com.thinkgem.jeesite.modules.weixin.task;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinTemplate;
import com.thinkgem.jeesite.modules.weixin.http.WeixinHttpCore;
import com.thinkgem.jeesite.modules.weixin.service.WeixinAPIService;
import com.thinkgem.jeesite.modules.weixin.service.WeixinTemplateService;
import com.thinkgem.jeesite.modules.weixin.vo.TemplateItemEMS;
import com.thinkgem.jeesite.modules.weixin.vo.WeixinTemplateMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lala on 16/11/12.
 */
@Service
@Lazy(false)
public class WeixinTemplateSendTask {

    @Autowired
    WeixinTemplateService weixinTemplateService;
    @Autowired
    WeixinHttpCore weixinHttpCore;
    @Autowired
    WeixinAPIService weixinAPIService;

    /**
     * 每5秒运行一次
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void exec(){
        WeixinTemplate template = new WeixinTemplate();
        template.setStatus("0");
        Page<WeixinTemplate> page = new Page<WeixinTemplate>();
        page.setPageNo(1);
        page.setPageSize(20);
        template.setPage(page);
        List<WeixinTemplate> templates = weixinTemplateService.findList(template);
        if(templates==null||templates.size()==0)return;
        //post到微信发送给用户
        for(WeixinTemplate weixinTemplate :templates){
            try{
                WeixinTemplateMsg<TemplateItemEMS> msg = JsonMapper.getInstance().fromJson(weixinTemplate.getMsg(),JsonMapper.getInstance().createCollectionType(WeixinTemplateMsg.class,TemplateItemEMS.class));
                String rep = weixinHttpCore.sendTemplateMsg(weixinAPIService.getOrNewToken(),msg);
                if(rep.contains("\"errcode\":0")){
                    //修改数据库中的状态
                    weixinTemplate.setStatus("5");//已发送
                    weixinTemplateService.save(weixinTemplate);
                }
            }catch (Exception e){}

        }

    }
}
