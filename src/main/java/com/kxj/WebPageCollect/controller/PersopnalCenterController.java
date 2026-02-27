package com.kxj.WebPageCollect.controller;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.controller
 * @Date 2022/9/20 22:32
 * @Version 1.0
 */

import com.kxj.WebPageCollect.ATO.ResponseBeanCode;
import com.kxj.WebPageCollect.entity.CollectEntity;
import com.kxj.WebPageCollect.entity.UserEntity;
import com.kxj.WebPageCollect.repsitory.userRepsitory;
import com.kxj.WebPageCollect.service.PersopnalCenterService;
import com.kxj.WebPageCollect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class PersopnalCenterController {

    @Autowired
    UserService userService;

    @Autowired
    PersopnalCenterService persopnalCenterService;

    @Autowired
    userRepsitory userRepistory;

    @Autowired
    HttpServletRequest request;

    @RequestMapping("/persopnal")
    public String PersopnalCenter(){

        UserEntity userentity = (UserEntity)request.getSession().getAttribute("userentity");

        request.setAttribute("persopnal",userService.UserByID(userentity.getId()));

         return "personalcenter";
    }

    @ResponseBody
    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    public ResponseBeanCode uploadImage(@RequestParam(value = "file") MultipartFile multipartFile) throws IOException {

        UserEntity userentity = (UserEntity)request.getSession().getAttribute("userentity");

        String key = userentity.getUsername() + userentity.getId();

        String imagepath = persopnalCenterService.uploadImage(multipartFile,userentity);

        return ResponseBeanCode.success(imagepath);
    }

    @ResponseBody
    @RequestMapping(value = "/updateUserName",method = RequestMethod.POST)
    public ResponseBeanCode uploadname(CollectEntity collect) throws IOException {
        UserEntity userentity = (UserEntity)request.getSession().getAttribute("userentity");

        if (collect.getTitle()!="") {
            userentity.setUsername(collect.getTitle());
            userRepistory.save(userentity);
            request.setAttribute("persopnal",userService.UserByID(userentity.getId()));
            return ResponseBeanCode.success("");
        }

        return ResponseBeanCode.error();
    }


}
