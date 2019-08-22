package com.zzj.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;


public class BaseEntity implements Serializable {


    public BaseEntity() {

    }
    @Override
    public String toString(){
        String end = "";
        end = JSON.toJSONString(this);
        return end;
    }
}
