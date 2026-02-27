package com.kxj.WebPageCollect.service;

import com.kxj.WebPageCollect.ATO.CollectAto;
import com.kxj.WebPageCollect.entity.CollectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.service.serviceimpl
 * @Date 2022/9/14 20:11
 * @Version 1.0
 */
public interface CollectService {

    List<CollectAto> getDateLineByUserID(Integer id);


    Page<CollectEntity> findUserCollexts(Integer userID, String dateline, Pageable pageable);

    void deleteById(Integer id);

    CollectEntity findByID(Integer id);

    void save(CollectEntity collect);

    Page<CollectEntity> collectsqare(Pageable pageable);
}
