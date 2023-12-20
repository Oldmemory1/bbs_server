package com.mxy.bbs_server.utility;

import java.io.File;

public class NginxHelper {

    public static String getAbsoluteUrl(String absolutePath) {
        //TODO: 应该对absolutePath处理一下
        File file = new File(absolutePath);
        return Const.NGINX_PORT + file.getAbsolutePath();
    }
}
