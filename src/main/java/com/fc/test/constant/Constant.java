package com.fc.test.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量信息类
 */
public class Constant {
    /**
     * VIDEO_MAX_SIZE
     * 视频最大限制
     */
    public static final long  VIDEO_MAX_SIZE = 200000000;
    /**
     * IMAGE_MAX_SIZE
     * 图片最大限制
     */
    public static final long  IMAGE_MAX_SIZE = 3145728;
    /**
     * FILE_HEADER_STREAM_MAP
     * 文件的头部流
     */
    public static final Map<String, String> FILE_HEADER_STREAM_MAP = new HashMap<String, String>();
    /**
     * IMAGE_HEADER_STREAM_MAP
     * 图片资源的头部流
     */
    public static final Map<String, String> IMAGE_HEADER_STREAM_MAP = new HashMap<String, String>();
    /**
     * VIDEO_HEADER_STREAM_MAP
     * 视频资源的头部流信息
     */
    public static final Map<String, String> VIDEO_HEADER_STREAM_MAP = new HashMap<String, String>();

    /**
     * 初始化
     */
    static {
        // images
        FILE_HEADER_STREAM_MAP.put("jpg", "FFD8FF");
        FILE_HEADER_STREAM_MAP.put("png", "89504E47");
        FILE_HEADER_STREAM_MAP.put("gif", "47494638");
        FILE_HEADER_STREAM_MAP.put("txt", "75736167");
        FILE_HEADER_STREAM_MAP.put("doc", "D0CF11E0");
        FILE_HEADER_STREAM_MAP.put("docx", "504B0304");
        FILE_HEADER_STREAM_MAP.put("mp4", "000000");
        FILE_HEADER_STREAM_MAP.put("MP4", "000000");
        FILE_HEADER_STREAM_MAP.put("wav", "57415645");
        FILE_HEADER_STREAM_MAP.put("flv", "464C5601");
        FILE_HEADER_STREAM_MAP.put("mp3", "49443304");
        FILE_HEADER_STREAM_MAP.put("xls", "D0CF11E0");
        FILE_HEADER_STREAM_MAP.put("xlsx", "504B0304");
        FILE_HEADER_STREAM_MAP.put("pdf", "255044462D312E");

        IMAGE_HEADER_STREAM_MAP.put("jpg", "FFD8FF");
        IMAGE_HEADER_STREAM_MAP.put("png", "89504E47");
        IMAGE_HEADER_STREAM_MAP.put("gif", "47494638");

        VIDEO_HEADER_STREAM_MAP.put("mp4", "000000");
        VIDEO_HEADER_STREAM_MAP.put("MP4", "000000");
        VIDEO_HEADER_STREAM_MAP.put("wav", "57415645");
        VIDEO_HEADER_STREAM_MAP.put("flv", "464C5601");
    }
}
