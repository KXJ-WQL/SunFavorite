package com.kxj.WebPageCollect.service.serviceimpl;

import com.kxj.WebPageCollect.entity.UserEntity;
import com.kxj.WebPageCollect.repsitory.userRepsitory;
import com.kxj.WebPageCollect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.service.serviceimpl
 * @Date 2022/9/20 22:39
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    userRepsitory userRepsitory;

    @Override
    public UserEntity UserByID(Integer id) {

        Optional<UserEntity> byId = userRepsitory.findById(id);

        return byId.isPresent()?byId.get():null;
    }
}
