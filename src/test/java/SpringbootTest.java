import com.boock.BoockApplication;
import com.boock.entity.po.Comment;
import com.boock.util.Base64Utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest(classes = BoockApplication.class)
public class SpringbootTest {


    public static long time = 1000*60*60*24;
    public static String APP_SECRET = "ymzldnrwrcccydzzgjwcwycicoywynswdswdwsndszdyykyyjhbzzjydkjdkbrhxybnzwjybbbblzdnldhxqdrsnyy";

    @Test
    void test01(){
        String filePath = "D:/SOFTWARE/CODE/idea/workspace/Boock/src/main/resources/static/uploadImage/";
        filePath = filePath.substring(filePath.lastIndexOf("/uploadImage"));
        System.out.println(filePath);
    }

    @Test
    void test02(){
//        String str = "<p>saldkfjkl</p><h1>skldjfwieo></h1><p>fff</p><img src=\"data:image/png;base64,iVBORw0KGgoAAA.....ggg==\" /><img src=\"data:image/png;base64,iVBORqwe2w0KGgoAAA.....ggg==\" />";
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        System.out.println(formattedDateTime); // 输出格式化后的日期时间字符串
    }

    @Test
    void Test03(){
        Comment zhangsan = Comment.builder()
                .id("123")
                .name("zhangsan")
                .createTime(LocalDateTime.of(2025,2,17,13,0,0))
                .build();
        Comment zhangsan2 = Comment.builder()
                .id("123")
                .name("zhangsan")
                .createTime(LocalDateTime.of(2025,2,17,14,0,0))
                .build();
        List<Comment> commentList = new ArrayList<>();
        commentList.add(zhangsan);


    }



    @Test
    void contextLoads() {
        String JwtToken = Jwts.builder()
                //头部
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //载荷
                .claim("username","admin")
                .setSubject("jwt-user")
                //token过期时间：1小时
                .setExpiration(new Date(System.currentTimeMillis()+time ))
                .setId(UUID.randomUUID().toString())//id字段
                //签名
                .signWith(SignatureAlgorithm.HS256,APP_SECRET)//签名加密算法和
                //连接字符串(.)；
                .compact();

        System.out.println(JwtToken);
    }
}

