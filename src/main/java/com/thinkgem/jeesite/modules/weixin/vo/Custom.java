package com.thinkgem.jeesite.modules.weixin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lala on 16/11/1.
 */
public class Custom {
    @JsonProperty("kf_account")
    private String kfAccount;

    @JsonProperty("kf_headimgurl")
    private String kfHeadimgurl;

    @JsonProperty("kf_id")
    private String kfId;

    @JsonProperty("kf_nick")
    private String kfNick;

    @JsonProperty("status")
    private int status;

    @JsonProperty("auto_accept")
    private String autoAccept;

    @JsonProperty("accepted_case")
    private String acceptedCase;

    public String getKfAccount() {
        return kfAccount;
    }

    public void setKfAccount(String kfAccount) {
        this.kfAccount = kfAccount;
    }

    public String getKfHeadimgurl() {
        return kfHeadimgurl;
    }

    public void setKfHeadimgurl(String kfHeadimgurl) {
        this.kfHeadimgurl = kfHeadimgurl;
    }

    public String getKfId() {
        return kfId;
    }

    public void setKfId(String kfId) {
        this.kfId = kfId;
    }

    public String getKfNick() {
        return kfNick;
    }

    public void setKfNick(String kfNick) {
        this.kfNick = kfNick;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAutoAccept() {
        return autoAccept;
    }

    public void setAutoAccept(String autoAccept) {
        this.autoAccept = autoAccept;
    }

    public String getAcceptedCase() {
        return acceptedCase;
    }

    public void setAcceptedCase(String acceptedCase) {
        this.acceptedCase = acceptedCase;
    }
}
