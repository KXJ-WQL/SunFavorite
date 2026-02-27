package com.kxj.WebPageCollect.controller;

import com.kxj.WebPageCollect.ATO.ResponseBeanCode;
import com.kxj.WebPageCollect.annotation.Login;
import com.kxj.WebPageCollect.entity.CollectEntity;
import com.kxj.WebPageCollect.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * @Author WQL-KXJ
 * @ProjectName WebPageCollect
 * @PackageName com.kxj.WebPageCollect.controller
 * @Date 2022/9/17 9:58
 * @Version 1.0
 */
@Controller
public class DateLineController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    CollectService collectService;

    @Login
    @RequestMapping("/collectflow/{userId}/{dateline}")
    @ResponseBody
    public ResponseBeanCode userCollects(@PathVariable(name = "userId") Integer userid,
                                         @PathVariable(name = "dateline") String dateline
    ){
        Page<CollectEntity> userCollexts = collectService.findUserCollexts(userid, dateline, getPage());

        request.getSession().removeAttribute("userid");

        List<CollectEntity> content = userCollexts.getContent();

        return ResponseBeanCode.success(content);
    }

    Pageable getPage() {

        Integer page = ServletRequestUtils.getIntParameter(request, "page", 1);
        Integer size = ServletRequestUtils.getIntParameter(request, "size", 10);
        //日期排序，日期一致按时间排序
        Sort sort = Sort.by(Sort.Order.desc("collecteddate"), Sort.Order.desc("collectedtime"));
        return PageRequest.of(page-1,size,sort);
    }


}
