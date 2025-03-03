package com.boock.interceptor;

import com.boock.util.CookieUtil;
import com.boock.util.JwtUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class JwtTokenUserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        String method = request.getMethod();
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();

        Map<String, String> map = CookieUtil.readCookie(request, "token");
        String token = map.get("token");
        if(token==null||!JwtUtil.checkToken(token)){
            if(request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with")
                    .equalsIgnoreCase("XMLHttpRequest")){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }else{
                //返回登录页
                response.sendRedirect("/loginPage.html");
                return false;
            }

        }
        return true;
    }
}
