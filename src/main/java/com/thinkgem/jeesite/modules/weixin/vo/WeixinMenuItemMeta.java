package com.thinkgem.jeesite.modules.weixin.vo;

import java.util.List;

/**
 * Created by lala on 16/11/2.
 */
public class WeixinMenuItemMeta {
    private String name;
    private String type;
    private String key;
    private String url;
    private List <WeixinMenuItemMeta> sub_button;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<WeixinMenuItemMeta> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<WeixinMenuItemMeta> sub_button) {
        this.sub_button = sub_button;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
