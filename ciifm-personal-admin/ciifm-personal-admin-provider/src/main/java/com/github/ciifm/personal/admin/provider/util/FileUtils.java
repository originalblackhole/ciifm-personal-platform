package com.github.ciifm.personal.admin.provider.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * <p></p>
 *
 * @author rui.zhou
 * @date 2019/1/25 11:06
 */
public class FileUtils {

    /**
     * 文件系统，根目录
     */
    public static final String DIR_MATERIAL = "snow/uploadfile/";

    /**
     * 生成文件key
     * @param dir 文件目录
     * @param suffix 文件后缀
     * @return 完整key
     */
    public static String createKey(String dir, String suffix){
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String code = UUID.randomUUID().toString().replaceAll("-", "");
        StringBuilder key = new StringBuilder(DIR_MATERIAL);
        key.append(dir).append("/").append(sf.format(new Date())).append("/").append(code).append(suffix);
        return key.toString();
    }

    /**
     * 生成文件key
     * @param dir 文件目录
     * @param suffix 文件后缀
     * @return 完整key
     */
    public static String createKey(String dir, String suffix,String idNo){
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String code = UUID.randomUUID().toString().replaceAll("-", "");
        StringBuilder key = new StringBuilder(DIR_MATERIAL);
        key.append(dir).append("/").append(sf.format(new Date())).append("/").append(idNo).append("/").append(code).append(suffix);
        return key.toString();
    }

}
