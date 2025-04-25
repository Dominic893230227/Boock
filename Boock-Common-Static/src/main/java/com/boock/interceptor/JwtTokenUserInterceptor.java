package com.boock.interceptor;

import com.boock.util.CookieUtil;
import com.boock.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)){
            return true;
        }
//        String method = request.getMethod();
//        StringBuffer requestURL = request.getRequestURL();
//        String queryString = request.getQueryString();

        Map<String, String> map = CookieUtil.readCookie(request, "token");
        String token = map.get("token");
        if(token==null||!JwtUtil.checkToken(token)){
            if(request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with")
                    .equalsIgnoreCase("XMLHttpRequest")){//判断是不是ajax请求 ，如果是，就返回403
                response.setStatus(javax.servlet.http.HttpServletResponse.SC_FORBIDDEN);
                return false;
            }else{
                //返回登录页
                response.sendRedirect("/loginPage.html");
                return false;
            }
        }

        StringBuffer requestURL1 = request.getRequestURL();
        String requestURI = request.getRequestURI();
//        System.out.println(requestURL1);//http://localhost:8080/web/user/loadUser
//        System.out.println(requestURI);///web/user/loadUser

        if(requestURI.startsWith("/backstage")){
            HashMap<String, Object> map1 = JwtUtil.readJwt(token);
//            System.out.println(map1);
            String id = (String) map1.get("id");
            if(!id.equals("1")){
                return false;
            }
        }

        return true;
    }
}
