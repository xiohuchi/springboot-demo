package com.demo.controller;

import com.kingdee.shr.sso.client.ltpa.LtpaTokenManager;
import com.kingdee.shr.sso.client.util.UrlUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangbin
 * @date 2020年12月14日
 */
@RestController
public class SHRController {

    @RequestMapping("/shr")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/shr/getToken")
    public String getToken() {
        return loginShrByUser("http://192.168.1.203:6888/shr");
    }

    public String loginShrByUser(String serverUrl) {
        String loginUrl = serverUrl + "/OTP2sso.jsp";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", "administrator");
        String password = LtpaTokenManager.generate((String) parameters.get("username"), LtpaTokenManager.getDefaultLtpaConfig(), "OTP").toString();
        parameters.put("password", password);
        parameters.put("userAuthPattern", "OTP");
        parameters.put("isNotCheckRelogin", "false");
        String url = UrlUtil.assembleUrl(loginUrl, parameters);
        System.out.println("单点登录 url: " + url);
        return url;
    }
}
