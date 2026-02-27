package com.kxj.WebPageCollect.handler;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.handler
 * @Date 2022/9/10 21:24
 * @Version 1.0
 */
@Component
public class TextHandler implements WxMpMessageHandler {

    @Autowired
    LoginHandler loginHandler;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {

        //接受到微信公众号的消息内容
        String content = wxMpXmlMessage.getContent();

        //获取用户的openid
        String fromUser = wxMpXmlMessage.getFromUser();

        //响应消息
        String outmessage;

        //根据不同的消息内容进行回复
        if(content.contains("验证码")){
            String saveuser = loginHandler.saveuser(fromUser);
            outmessage=saveuser;
        }else {
            outmessage="消息无法识别！";
        }

        //构建响应消息对象
        WxMpXmlOutTextMessage wxMpXmlOutTextMessage = WxMpXmlOutMessage.TEXT().content(outmessage).fromUser(wxMpXmlMessage.getToUser()).toUser(wxMpXmlMessage.getFromUser()).build();
        return wxMpXmlOutTextMessage;
    }
}
