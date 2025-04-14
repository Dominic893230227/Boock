package com.boock.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class CookieUtil {

    public static void addCookie(HttpServletResponse response, String path,
                                 String name, String value, int maxAge, boolean httpOnly){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(-1);
        cookie.setPath(path);
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
    }

    public static Map<String, String> readCookie(HttpServletRequest request, String... cookieNames) {//根据cookieNames获取对应的cookie
        Map<String, String> cookieMap = new HashMap<String, String>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();
                for (int i = 0; i < cookieNames.length; i++) {
                    if (cookieNames[i].equals(cookieName)) {
                        cookieMap.put(cookieName, cookieValue);
                    }
                }
            }
        }
        return cookieMap;
    }

    public static void cleanToken(HttpServletResponse response){
        Cookie cookie = new Cookie("token",null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
