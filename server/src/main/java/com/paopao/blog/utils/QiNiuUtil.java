package com.paopao.blog.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/14 9:47
 * @description：七牛云配置信息
 */

public class QiNiuUtil {

    public static final String ACCESSKEY = "3t0z9Ob0rJ3OMD3l-GWcGeBabwiVeE5PZCP8Gu3y";

    public static final String SECRETKEY = "EmgovoluIwmSbWLVb7xbuQF7WUDqUmWdPrHhdMP5";

    //存储空间名
    public static final String BUCKET = "h5-childrenprogramming";

    //存储空间访问的域名
    public static final String PATH = "http://h5.paopaovampire.club";

    public static String uploadImageToQiNiu(FileInputStream inputStream) {
        Configuration configuration = new Configuration(Zone.huanan());
        UploadManager uploadManager = new UploadManager(configuration);
        //生成上传凭证，然后准备上传
        try {
            Auth auth = Auth.create(ACCESSKEY, SECRETKEY);
            String upToken = auth.uploadToken(BUCKET);
            try {
                Response response = uploadManager.put(inputStream, "article-picture/"+getRandomName(), upToken, null, null);
                DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                String returnPath = PATH + "/" + defaultPutRet.key;
                return returnPath;
            } catch (QiniuException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "F";
        }
        return "F";
    }


    //生成随机文件名
    public static String getRandomName() {
        Random random = new Random();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(random.nextInt(100) + 100);
        stringBuffer.append(format.format(new Date()));
        stringBuffer.append(random.nextInt(100) + 100);
        return stringBuffer.toString();
    }


}
