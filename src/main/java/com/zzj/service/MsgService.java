package com.zzj.service;

import com.google.common.collect.Lists;
import com.zzj.entity.Msg;
import com.zzj.entity.MsgExample;
import com.zzj.mapper.MsgMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2019/1/28.
 */
@Slf4j
@Service
@SuppressWarnings(value = "all")
public class MsgService {


    @Autowired
    private MsgMapper msgMapper;

    public List<Msg> findAll(String targetUserId,String rua){
        List<Msg> msgList = Lists.newArrayList();
        MsgExample msgExample = new MsgExample();
        MsgExample.Criteria criteria = msgExample.createCriteria();
        criteria.andTargetEqualTo(targetUserId);
        if(!StringUtils.isEmpty(rua)) {
            criteria.andReadEqualTo(Integer.parseInt(rua));
        }
        msgList = msgMapper.selectByExample(msgExample);
        return msgList;
    }
    public void readit(String msgid){
        Msg msg = new Msg();
        msg.setId(msgid);
        msg.setRead(1);
        msgMapper.updateByPrimaryKeySelective(msg);
    }

    public void saveit(Msg msg){
        msgMapper.insert(msg);
    }

}
