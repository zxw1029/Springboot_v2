package com.fc.test.config;

import com.fc.test.config.intercepors.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登陆拦截器
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
        // 拦截路径
        loginRegistry.addPathPatterns("/**");
        // 排除路径
        loginRegistry.excludePathPatterns("/");
        loginRegistry.excludePathPatterns("login");
        loginRegistry.excludePathPatterns("loginout");
        // 排除资源请求
        loginRegistry.excludePathPatterns("/admin/**/*.jpg");
        loginRegistry.excludePathPatterns("/admin/**/*.png");
        loginRegistry.excludePathPatterns("/admin/**/*.gif");
        loginRegistry.excludePathPatterns("/admin/**/*.js");
        loginRegistry.excludePathPatterns("/admin/**/*.css");
        loginRegistry.excludePathPatterns("/js/**/*.js");
        loginRegistry.excludePathPatterns("/login2/**/*.jpg");
        loginRegistry.excludePathPatterns("/login2/**/*.gif");
        loginRegistry.excludePathPatterns("/login2/**/*.png");
        loginRegistry.excludePathPatterns("/login2/**/*.js");
        loginRegistry.excludePathPatterns("/login2/**/*.css");

        //注册文件上传拦截器
        FileUploadInterceptor fileUploadInterceptor = new FileUploadInterceptor();
        InterceptorRegistration fileUploadRegistry = registry.addInterceptor(fileUploadInterceptor);
        fileUploadRegistry.addPathPatterns("/**");

        //注册文件下载拦截器
        FileDownloadInterceptor fileDownloadInterceptor = new FileDownloadInterceptor();
        InterceptorRegistration fileDownloadRegistry = registry.addInterceptor(fileDownloadInterceptor);
        fileDownloadRegistry.addPathPatterns("/**/*.pdf");
        fileDownloadRegistry.addPathPatterns("/**/*.doc");

        //注册图片上传拦截器
        ImageUploadInterceptor imageUploadInterceptor = new ImageUploadInterceptor();
        InterceptorRegistration imageUploadRegistry = registry.addInterceptor(imageUploadInterceptor);
        imageUploadRegistry.addPathPatterns("/**");

        //注册视频上传拦截器
        VideoUploadInterceptor videoUploadInterceptor = new VideoUploadInterceptor();
        InterceptorRegistration videoUploadRegistry = registry.addInterceptor(videoUploadInterceptor);
        videoUploadRegistry.addPathPatterns("/**");
    }
}
