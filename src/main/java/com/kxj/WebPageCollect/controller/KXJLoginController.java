package com.kxj.WebPageCollect.controller;

import com.kxj.WebPageCollect.ATO.ResponseBeanCode;
import com.kxj.WebPageCollect.service.serviceimpl.GetCodeRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.controller
 * @Date 2022/9/10 16:49
 * @Version 1.0
 */
@Controller
public class KXJLoginController {

    @Autowired
    GetCodeRedis getCodeRedis;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/login")
    public String Getlogin(){
        return "login";
    }


    @ResponseBody
    @PostMapping(value = "/login",produces = "application/json;charset=UTF-8")
    public ResponseBeanCode Postlogin(@RequestParam(name = "yzcode") String code){

        String userid = getCodeRedis.getcode("KXJ" + code);

        if (!ObjectUtils.isEmpty(userid)){

            return ResponseBeanCode.success(userid);
        }
        return ResponseBeanCode.error();
    }

    @GetMapping("/logout")
    public String logout(){

        request.getSession().removeAttribute("userentity");

        request.removeAttribute("dateline");

        return "login";
    }

}
