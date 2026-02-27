package com.kxj.WebPageCollect.controller;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.controller
 * @Date 2022/9/10 21:36
 * @Version 1.0
 */
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class WxMessageController {

    @Autowired
    WxMpService wxMpService;

    @Autowired
    WxMpMessageRouter wxMpMessageRouter;

    @PostMapping(value = "/", produces = "application/xml; charset=UTF-8")
    public String handleMessage(@RequestBody String requestBody) {

        WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);

        WxMpXmlOutMessage outMessage;
        try {
            // 将消息路由给对应的处理器，获取响应
            outMessage = wxMpMessageRouter.route(inMessage);
        } catch (Exception e) {
            outMessage = null;
        }
        // 将响应消息转换为xml格式返回
        return outMessage == null ? "" : outMessage.toXml();
    }
}
