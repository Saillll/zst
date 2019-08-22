package com.zzj.service;

import com.alibaba.fastjson.JSON;
import com.zzj.common.Hela;
import com.zzj.common.Odin;
import com.zzj.entity.User;
import com.zzj.entity.UserExample;
import com.zzj.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/11/15.
 */
@Slf4j
@Service
@SuppressWarnings(value = "all")
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public String findAll(){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdIsNotNull();
        User user=userMapper.selectByPrimaryKey("1");
        List<User> list = userMapper.selectByExample(userExample);
        //list.add(user);
        String json = JSON.toJSONString(list);
        System.out.println(json);
        return json;
    }
    public User getById(String id){
        User user = new User();
        user = userMapper.selectByPrimaryKey(id);
        return user;
    }
    public User getByUsername(String username){
        User user = null;
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUEqualTo(username);
        List<User> userList= userMapper.selectByExample(userExample);
        if(userList.size()> 0){
            user = userList.get(0);
        }
        return user;
    }
    public void save(User user) {
        boolean exists = false;
        boolean done = true;
        User judger = this.getById(user.getId());
        if(ObjectUtils.isEmpty(judger)){
            userMapper.insert(user);
        }else{
            userMapper.updateByPrimaryKeySelective(user);
        }
        return;
    }
    public Odin regist(User user) {
        Odin odin = new Odin();
        boolean exists = false;
        boolean done = true;
        User judger = this.getByUsername(user.getU());
        try{
            if(ObjectUtils.isEmpty(judger)){
                userMapper.insert( user);
            }else{
                odin.setHela(Hela.DuplicateUser);
            }
        }catch (Exception e){
            odin.setHela(Hela.Immortal);
        }
        return odin;
    }
    public boolean dualCheck(String u){
        boolean isUnique = true;
        User user = null;
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUEqualTo(u);
        List<User> userList= userMapper.selectByExample(userExample);
        if(userList.size()> 0){
            isUnique = false;
        }
        return isUnique;
    }

}
