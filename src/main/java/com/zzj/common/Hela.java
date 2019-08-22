package com.zzj.common;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回值信息
 */
public enum Hela {
    SUCCESS(0,"成功"),
    FAIL(-1,"失败"),
    ServerErr(-2,"服务器处理错误"),
    AUTOREFRESHTOKEN(1,"自动刷新TOKEN"),

    /**
     * 登陆
     */
    NoUser(600,"不存在此用户"),
    WrongPassword(601,"错误的密码"),
    AuthLess(602,"请登录"),
    TimeOut(603,"用户信息已过期，请重新登陆"),
    WrongKey(604,"错误的密码"),
    IllegalRequest(605,"错误的请求"),
    TokenMissed(606,"请重新登录"),
    DuplicateUser(607,"已存在此登陆名"),
    Immortal(-999,"未知错误");



    private final int code;
    private final String info;

    Hela(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    @JsonValue
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        //map.put("value", this.value);
        map.put("code", this.code);
        map.put("info", this.info);
        return map;
    }

}
