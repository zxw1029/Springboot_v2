package com.fc.test.util;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CommonUtils {
    /**
     * 将信息返回前端
     * @param response
     * @param json
     */
    public static void responseJson(HttpServletResponse response, JSONObject json){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null)
            {
                writer.close();
                writer = null;
            }
        }
    }
}
