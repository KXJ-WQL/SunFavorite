package com.kxj.WebPageCollect.controller;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.controller
 * @Date 2022/9/10 20:33
 * @Version 1.0
 */
@RestController
public class WxCheckoutController {

    @Autowired
    WxMpService wxMpService;

    @GetMapping("/")
    public String checkout(String signature,String timestamp,String nonce,String echostr){
        //效验签名
        if (wxMpService.checkSignature(timestamp, nonce, signature)){
            //效验成功后返回echostr
            return echostr;
        }
        //效验失败
        return null;
    }


}
