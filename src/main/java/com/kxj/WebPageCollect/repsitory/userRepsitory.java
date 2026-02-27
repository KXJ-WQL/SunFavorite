package com.kxj.WebPageCollect.repsitory;

import com.kxj.WebPageCollect.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.repsitory
 * @Date 2022/9/11 11:20
 * @Version 1.0
 */

@Repository
public interface userRepsitory extends CrudRepository<UserEntity, Integer> {

    @Query("from UserEntity user where user.openid=?1")
    public UserEntity findByOpenid(String openid);



}
