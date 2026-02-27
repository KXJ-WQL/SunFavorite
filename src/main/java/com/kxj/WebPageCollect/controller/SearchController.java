package com.kxj.WebPageCollect.controller;

import com.kxj.WebPageCollect.ATO.ResponseBeanCode;
import com.kxj.WebPageCollect.annotation.Login;
import com.kxj.WebPageCollect.entity.CollectEntity;
import com.kxj.WebPageCollect.entity.esentity.EsCollectEntity;
import com.kxj.WebPageCollect.service.CollectService;
import com.kxj.WebPageCollect.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.controller
 * @Date 2022/9/20 0:36
 * @Version 1.0
 */
@Controller
public class SearchController {

    @Autowired
    SearchService searchService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    CollectService collectService;

    @Login
    @GetMapping("/search")
    public String search(String q, Integer userId) {

        String message = "正在搜索收藏记录~~";

        request.setAttribute("searchTip", message);
        request.setAttribute("q", q);

        return "collect-square";
    }

    @Login
    @RequestMapping("/collects/square")
    @ResponseBody
    public ResponseBeanCode collect_square(@RequestParam("page") Integer page,
                                   @RequestParam("size") Integer size,String q, Integer userId){

        PageRequest pages = PageRequest.of(page-1, size);

        if (ObjectUtils.isEmpty(q)){

            Page<CollectEntity> collectsqare = collectService.collectsqare(pages);

            return ResponseBeanCode.success(collectsqare.getContent());

        }


        List<CollectEntity> collectsqare = searchService.search(q,pages);

        return ResponseBeanCode.success(collectsqare);
    }



}
