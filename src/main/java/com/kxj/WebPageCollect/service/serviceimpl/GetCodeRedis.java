package com.kxj.WebPageCollect.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/*
 * @Author WQL-KXJ
 *
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.service
 * @Date 2022/9/11 18:33
 * @Version 1.0
 */
@Service
public class GetCodeRedis {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public String getcode(String key){

        String userid = stringRedisTemplate.opsForValue().get(key);
        Boolean delete = stringRedisTemplate.delete(key);

        if(!ObjectUtils.isEmpty(userid) && delete){

            return userid;
        }
        return null;
    }

}
