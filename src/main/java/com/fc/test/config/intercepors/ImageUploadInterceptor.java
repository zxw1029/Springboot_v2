package com.fc.test.config.intercepors;

import com.fc.test.constant.Constant;
import com.fc.test.util.CommonUtils;
import com.fc.test.util.FileHeaderHelperUtils;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 图片上传拦截器
 */
public class ImageUploadInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        boolean isSuccessFlag = false;
        JSONObject json = new JSONObject();
        if(request != null && ServletFileUpload.isMultipartContent(request)) {
            MultipartHttpServletRequest mtsr = (MultipartHttpServletRequest)request;
            Iterator<String> it = mtsr.getFileNames();
            while(it.hasNext()) {
                List<MultipartFile> multipartFiles = mtsr.getFiles(it.next());
                //也可以根据MultipartFile的ContentType进行过滤，如jpg的ContentType为ContentType
                for(MultipartFile multipartFile : multipartFiles) {
                    if(null == multipartFile || multipartFile.isEmpty())
                    {
                        json.put("code", "error");
                        json.put("msg", "请不要上传空的图片资源!");
                        CommonUtils.responseJson(response, json);
                        isSuccessFlag = false;
                        break;
                    }

                    //获取上传文件的头部流信息
                    String path = multipartFile.getOriginalFilename();
                    String fileType = FileHeaderHelperUtils.getFileType(path);
                    //获取已知常用文件的头部流信息
                    Map<String, String> mFileTypes = Constant.IMAGE_HEADER_STREAM_MAP;
                    List<String> values = (List<String>) mFileTypes.values();
//                    values.stream().filter(value -> {
//                        if(StringUtils.startsWith(fileType,value)){
//                            isFound = true ;
//                        }
//                        return isFound;
//                    }).findAny();
                    boolean isFound = false;
                    for(String value : values){
                        if(StringUtils.startsWith(fileType,value)){
                            isFound = true ;
                            break;
                        }
                    }
                    if(!isFound)
                    {
                        json.put("code", "error");
                        json.put("msg", "请上传jpg/png/gif资源!");
                        CommonUtils.responseJson(response, json);
                        isSuccessFlag = false;
                        break;
                    }
                    long size = multipartFile.getSize();
                    long maxSize = Constant.IMAGE_MAX_SIZE;
                    if(size > maxSize)
                    {
                        isSuccessFlag = false;
                        json.put("code", "error");
                        json.put("msg", "请上传小于3M的jpg/png/gif资源!");
                        CommonUtils.responseJson(response, json);
                        break;
                    }
                    isSuccessFlag = true;
                }
            }
        }
        if(isSuccessFlag)
        {
            json.put("code", "success");
            json.put("msg", "上传成功!");
            CommonUtils.responseJson(response, json);
        }
        return isSuccessFlag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
