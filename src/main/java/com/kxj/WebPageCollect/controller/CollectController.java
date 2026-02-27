package com.kxj.WebPageCollect.controller;

import com.kxj.WebPageCollect.ATO.ResponseBeanCode;
import com.kxj.WebPageCollect.entity.CollectEntity;
import com.kxj.WebPageCollect.entity.UserEntity;
import com.kxj.WebPageCollect.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.controller
 * @Date 2022/9/15 14:39
 * @Version 1.0
 */
@RestController
public class CollectController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    CollectService collectService;


    @GetMapping("/collect/delete")
    public ResponseBeanCode deleteCollect(@RequestParam("id") Integer id){

        CollectEntity byID = collectService.findByID(id);
        UserEntity userEntity = (UserEntity) request.getSession().getAttribute("userentity");

        if(ObjectUtils.isEmpty(byID)){
            return ResponseBeanCode.error();
        }else if(userEntity.getId() != byID.getUser().getId()){
            return ResponseBeanCode.error();
        }

        collectService.deleteById(id);

        return ResponseBeanCode.success(null);
    }

    @PostMapping("/collect/save")
    public ResponseBeanCode saveCollect(CollectEntity collect) {

        UserEntity userentity = (UserEntity)request.getSession().getAttribute("userentity");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userentity.getId());

        collect.setUser(userEntity);
        collectService.save(collect);

        return ResponseBeanCode.success(null);
    }




}
