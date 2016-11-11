package com.thinkgem.jeesite.modules.weixin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lala on 16/11/11.
 */
public class WeixinTemplateMsg<E> {

    private String touser;
    @JsonProperty("template_id")
    private String templateId;
    private String url;
    private String topcolor;
    private E data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
