package com.boock.configuration;

import com.boock.interceptor.JwtTokenUserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtTokenUserInterceptor jwtTokenUserInterceptor;


    @Value("${file.path.windows}")
    private String windowsHeadPhotoPath;
    @Value("${file.path.linux}")
    private String linuxHeadPhotoPath;
    @Value("${file.boockPath.windows}")
    private String windowsBoockImgPath;
    @Value("${file.boockPath.linux}")
    private String linuxBoockImgPath;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/login/registe", "/loginPage.html", "/registerPage.html","/common/**", "/css/**", "/js/**", "/images/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
            registry.addResourceHandler("/headPhoto/**").addResourceLocations("file:" + windowsHeadPhotoPath);
            registry.addResourceHandler("/boockImg/**").addResourceLocations("file:" + windowsBoockImgPath);
        } else { //linux和mac系统
            registry.addResourceHandler("/headPhoto/**").addResourceLocations("file:" + linuxHeadPhotoPath);
            registry.addResourceHandler("/boockImg/**").addResourceLocations("file:" + linuxBoockImgPath);
        }
    }
}
