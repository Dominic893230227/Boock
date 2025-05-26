package com.boock.service.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MyWechatService {

    @Autowired
    private WxMpService wxMpService;

    public Map<String,Object> getAccessTokenFromService() {
        HashMap<String, Object> map = new HashMap<>();
        try {
            WxMpUserService userService = wxMpService.getUserService();
            WxMpUser fuck = userService.userInfo("o5AB46LHA_BOo1DmzdVCU2LYpvY");
//            return wxMpService.getAccessToken();、
            map.put("WxMpUser",fuck);
            return map;
        } catch (WxErrorException e) {
            e.printStackTrace();
            map.put("msg","获取 Access Token 失败: " + e.getMessage());
            return map ;
        }
    }
}
