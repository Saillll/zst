package com.zzj.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    public  static String CHANNEL_KEY;
    public static Integer PAGE_SIZE;

    @Value(value = "${spring.redis.pubsub.keywords.unsubscribe}")
    public  void setChannelKey(String channelKey) {
        CHANNEL_KEY = channelKey;
    }

    @Value(value = "${page.pagesize}")
    public  void setPageSize(Integer pageSize) {
        PAGE_SIZE = pageSize;
    }
}
