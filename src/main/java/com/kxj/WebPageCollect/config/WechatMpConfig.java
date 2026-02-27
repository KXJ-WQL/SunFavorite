package com.kxj.WebPageCollect.config;

import com.kxj.WebPageCollect.handler.TextHandler;
import lombok.Data;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.config
 * @Date 2022/9/10 20:08
 * @Version 1.0
 */
@Configuration
public class WechatMpConfig {//配置消息路由规则

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private TextHandler textHandler;

    @Bean
    public WxMpMessageRouter mpMessageRouter(){

        //创建消息路由
        WxMpMessageRouter router =new WxMpMessageRouter(wxMpService);

        //添加文本消息处理器到路由
        router.rule().async(false).msgType(WxConsts.XmlMsgType.TEXT).handler(textHandler).end();

        return router;
    }



}
