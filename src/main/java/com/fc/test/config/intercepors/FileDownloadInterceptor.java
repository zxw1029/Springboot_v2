package com.fc.test.config.intercepors;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

public class FileDownloadInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String filename = new String(URLDecoder.decode(StringUtils.substringAfterLast(request.getRequestURI(), "/"), "utf-8").replaceAll(" ", "%20").replaceAll("\\+", "%20").getBytes("utf-8"), "ISO-8859-1");
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        response.setContentType("application/octet-stream;charset=utf-8");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
