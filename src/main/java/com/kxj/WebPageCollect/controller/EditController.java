package com.kxj.WebPageCollect.controller;

import com.kxj.WebPageCollect.annotation.Login;
import com.kxj.WebPageCollect.entity.CollectEntity;
import com.kxj.WebPageCollect.entity.UserEntity;
import com.kxj.WebPageCollect.exceptions.collException;
import com.kxj.WebPageCollect.service.CollectService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.controller
 * @Date 2022/9/16 15:21
 * @Version 1.0
 */
@Controller
public class EditController {

    @Autowired
    CollectService collectService;

    @Autowired
    HttpServletRequest request;

    @Value("${server.domain}")
    String domain;

    @Login
    @GetMapping("/collect/edit")
    public String getedit(CollectEntity collectEntity){

        String js = "javascript:(function(){" +
                "var site='" + domain +"/collect/edit" +
                "?title='+encodeURIComponent(document.title)+'" +
                "&url='+encodeURIComponent(document.URL);" +
                "var win=window.open(site,'_blank');" +
                "win.focus();})()";
        if(collectEntity.getId()==null){
            request.setAttribute("js",js);
            request.setAttribute("collect",collectEntity);
            return "edit";
        }

        CollectEntity byID = collectService.findByID(collectEntity.getId());
        UserEntity userentity = (UserEntity)request.getSession().getAttribute("userentity");

        if(byID == null){
            throw new collException("id不存在");
        }else if(userentity.getId() != byID.getUser().getId()){
            throw new collException("没有权限");
        }

        request.setAttribute("js",js);
        request.setAttribute("collect",byID);
        return "edit";
    }



}
