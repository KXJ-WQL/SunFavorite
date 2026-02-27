package com.kxj.WebPageCollect.service;

import com.kxj.WebPageCollect.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.service
 * @Date 2022/9/20 22:38
 * @Version 1.0
 */
public interface UserService {

    public UserEntity UserByID(Integer id);


}
