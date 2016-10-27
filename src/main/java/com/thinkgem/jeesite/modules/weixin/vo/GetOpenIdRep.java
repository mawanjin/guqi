package com.thinkgem.jeesite.modules.weixin.vo;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by lala on 16/10/27.
 */
public class GetOpenIdRep {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expiresIn")
    private long expires_in;

    @JsonProperty("refresh_token")
    private long refreshToken;

    @JsonProperty("openid")
    private long openid;

    @JsonProperty("scope")
    private long scope;

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

    public long getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(long refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getOpenid() {
        return openid;
    }

    public void setOpenid(long openid) {
        this.openid = openid;
    }

    public long getScope() {
        return scope;
    }

    public void setScope(long scope) {
        this.scope = scope;
    }
}
