package com.kxj.WebPageCollect.handler;

import cn.hutool.core.util.RandomUtil;
import com.kxj.WebPageCollect.entity.UserEntity;
import com.kxj.WebPageCollect.repsitory.userRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.handler
 * @Date 2022/9/11 10:43
 * @Version 1.0
 */
@Component
public class LoginHandler {

    @Autowired
    userRepsitory userRepsitory;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    UserEntity userEntity;

    public String saveuser(String openid){

        if(!finduser(openid)){
            userEntity = new UserEntity();
            userEntity.setOpenid(openid);
            userEntity.setUsername("KXJ"+RandomUtil.randomString(3));
            userEntity.setAvater("/images/logo.png");
            userEntity.setCreatetime(LocalDateTime.now());
            userRepsitory.save(userEntity);
//            httpServletRequest.setAttribute("userbs",1);
//            httpServletRequest.setAttribute("userid",save.getId());
//            httpServletRequest.getSession().setAttribute("userentity",save);
        }

        String saveredis = saveredis(openid);

        return saveredis;
    }

    private Boolean finduser(String openid){

        //查询用户是否已经存在
        UserEntity byOpenid = userRepsitory.findByOpenid(openid);

        if(!ObjectUtils.isEmpty(byOpenid)){

            return true;
        }
        return false;
    }

    //生成验证码并保存到Redis中
    private String saveredis(String opid){

        String random = RandomUtil.randomString(5);

        stringRedisTemplate.opsForValue().set("KXJ"+random,opid,3, TimeUnit.MINUTES);

        return random;
    }

    //把用户id存入redis


}
