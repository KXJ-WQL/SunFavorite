package com.kxj.WebPageCollect.service;

import com.kxj.WebPageCollect.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.service.serviceimpl
 * @Date 2022/9/20 23:39
 * @Version 1.0
 */public interface PersopnalCenterService {

    public String uploadImage(MultipartFile file, UserEntity userentity) throws IOException;
}
