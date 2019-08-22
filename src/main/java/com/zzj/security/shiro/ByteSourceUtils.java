package com.zzj.security.shiro;

import org.apache.shiro.util.ByteSource;

/**
 * Created by Administrator on 2018/8/28.
 */
public class ByteSourceUtils {
    public static ByteSource bytes(byte[] bytes){
        return new SimpleByteSource(bytes);
    }
    public static ByteSource bytes(String arg0){
        return new SimpleByteSource(arg0.getBytes());
    }

}
