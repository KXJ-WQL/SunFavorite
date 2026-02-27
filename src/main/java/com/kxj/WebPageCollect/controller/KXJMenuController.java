package com.kxj.WebPageCollect.controller;

import com.kxj.WebPageCollect.ATO.CollectAto;
import com.kxj.WebPageCollect.annotation.Login;
import com.kxj.WebPageCollect.entity.UserEntity;
import com.kxj.WebPageCollect.repsitory.collectRepsitory;
import com.kxj.WebPageCollect.repsitory.userRepsitory;
import com.kxj.WebPageCollect.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.controller
 * @Date 2022/9/11 21:27
 * @Version 1.0
 */
@Controller
public class KXJMenuController {

    @Autowired
    CollectService collectService;

    @Autowired
    userRepsitory userRepsitory;

    @Autowired
    HttpServletRequest request;


    @RequestMapping("/menu/{userid}")
    public String menu(@PathVariable("userid") String userid){

        if(userid.equals("undefined")){
            UserEntity userentity = (UserEntity)request.getSession().getAttribute("userentity");
            userid = userentity.getOpenid();
        }

        UserEntity byOpenid = userRepsitory.findByOpenid(userid);

        request.getSession().setAttribute("userentity",byOpenid);

        List<CollectAto> dateLineByUser = collectService.getDateLineByUserID(byOpenid.getId());

        request.setAttribute("dateline",dateLineByUser);

        return "mainmenu";
    }

    @Login
    @RequestMapping("/collmenu/{id}")
    public String collmenu(@PathVariable("id") Integer id){

        List<CollectAto> dateLineByUser = collectService.getDateLineByUserID(id);

        request.getSession().setAttribute("userid",id);
        request.setAttribute("dateline",dateLineByUser);

        return "mainmenu";
    }

    @RequestMapping("/menu")
    public String menu(){

        return "mainmenu";
    }

    @RequestMapping("/sqare")
    public String sqare(){

        return "collect-square";
    }


}
