package com.zzj.common;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.ObjectUtils;

/**
 * @author DD-NEWGAME
 * @date 2018年12月7日
 */
public class Odin {

    private Hela hela;
    private Object loki;
    private Object thor;


    public Odin(Hela hela, Object loki) {
        this.hela = hela;
        this.loki = loki;
    }

    public Odin(Hela hela, Object loki,Object thor) {
        this.hela = hela;
        this.loki = loki;
        this.thor = thor;
    }


    public Odin(Hela hela) {
        this.hela = hela;
    }

    public Odin() {
        this.hela = Hela.SUCCESS;
    }

    //@JsonProperty(value="hela")
    public Hela getHela() {
        return hela;
    }

    public void setHela(Hela hela) {
        this.hela = hela;
    }

    //@JsonProperty(value="loki")
    public Object getLoki() {
        return loki;
    }

    public void setLoki(Object loki) {
        this.loki = loki;
    }

    public Object getThor() {
        return thor;
    }

    public void setThor(Object thor) {
        this.thor = thor;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
