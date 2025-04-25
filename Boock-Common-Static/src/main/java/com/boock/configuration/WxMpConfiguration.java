package com.boock.configuration;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WxMpConfiguration {

    @Value("${wechat.mp.app-id}")
    private String appId;

    @Value("${wechat.mp.secret}")
    private String secret;

    @Value("${wechat.mp.token}")
    private String token;

    @Value("${wechat.mp.aes-key}")
    private String aesKey;

    @Bean
    public WxMpService wxMpService() {
        WxMpDefaultConfigImpl configStorage = new WxMpDefaultConfigImpl();
        configStorage.setAppId(appId);
        configStorage.setSecret(secret);
        configStorage.setToken(token);
        configStorage.setAesKey(aesKey);

        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(configStorage);
        return wxService;
    }
}
