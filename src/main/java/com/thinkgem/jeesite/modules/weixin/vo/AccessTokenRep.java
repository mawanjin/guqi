package com.thinkgem.jeesite.modules.weixin.vo;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lala on 16/10/27.
 */
public class AccessTokenRep {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expiresIn")
    private long expires_in;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}
