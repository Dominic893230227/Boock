package com.boock.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class JwtUtil {


    public static long time = 1000*60*60*5;
    public static String APP_SECRET = "ymzldnrwrcccydzzgjwcwycicoywynswdswdwsndszdyykyyjhbzzjydkjdkbrhxybnzwjybbbblzdnldhxqdrsnyy";

    public static String creatJwtToken(Integer id,String username,String name){

        String jwtToken = Jwts.builder()
                //头部
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //载荷
                .claim("id",id)
                .claim("username",username)
                .claim("name",name)
                .setSubject("jwt-user")

                .setExpiration(new Date(System.currentTimeMillis()+time ))
                .setId(UUID.randomUUID().toString())//id字段
                //签名
                .signWith(SignatureAlgorithm.HS256,APP_SECRET)//签名加密算法和
                //连接字符串(.)；
                .compact();

        return jwtToken;
    }

    public static boolean checkToken(String jwtToken){
        if(jwtToken==null||jwtToken.equals("")){
            return false;
        }
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static HashMap<String, Object> readJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(APP_SECRET)
                .parseClaimsJws(token)
                .getBody();

        String id = claims.get("id").toString();
        String username = (String) claims.get("username");
        String name = (String) claims.get("name");
        Date date = claims.getExpiration();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 年-月-日 时:分:秒
        String expiration = formatter.format(date);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("username",username);
        map.put("name",name);
        map.put("expiration",expiration);
        return map;
    }

}
