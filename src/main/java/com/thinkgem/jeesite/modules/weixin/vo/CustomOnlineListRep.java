package com.thinkgem.jeesite.modules.weixin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by lala on 16/11/1.
 */
public class CustomOnlineListRep {
    @JsonProperty("kf_online_list")
    private List<Custom> kfList;

    public List<Custom> getKfList() {
        return kfList;
    }

    public void setKfList(List<Custom> kfList) {
        this.kfList = kfList;
    }
}
