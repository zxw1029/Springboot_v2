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
 * 文件上传拦截器
 * 文件上传大部分通过web前端判断后尾名或者service后端判断后尾名，这种操作具有一定的风险，
 * 比如：我可以将一个jsp页面，修改后尾名改成jpg文件进行上传，由于图片预览功能，这个文件会被执行，
 * 这时就可以发送用户数据到指定的服务下，窃取用户信息。本篇博文通过文件流头部判断文件类型，不同的文件具有不同的头部
 */
public class FileUploadInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
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
                        json.put("msg", "请不要上传空资源!");
                        CommonUtils.responseJson(response, json);
                        isSuccessFlag = false;
                        break;
                    }

                    //获取上传文件的头部流信息
                    String path = multipartFile.getOriginalFilename();
                    String fileType = FileHeaderHelperUtils.getFileType(path);
                    //获取已知常用文件的头部流信息
                    Map<String, String> mFileTypes = Constant.FILE_HEADER_STREAM_MAP;
                    List<String> values = (List<String>) mFileTypes.values();
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
                        json.put("msg", "请上传jpg/png/gif/txt/doc/mp4/flv/mp3/wav/xls/xlsx资源!");
                        CommonUtils.responseJson(response, json);
                        isSuccessFlag = false;
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
