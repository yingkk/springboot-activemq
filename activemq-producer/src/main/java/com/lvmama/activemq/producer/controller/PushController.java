package com.lvmama.activemq.producer.controller;

import com.lvmama.activemq.api.entity.News;
import com.lvmama.activemq.api.entity.User;
import com.lvmama.activemq.producer.service.PushService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/push/")
public class PushController {

    @Resource(name = "userPushService")
    private PushService userPushService;
    @Resource(name = "newsPushService")
    private PushService newsPushService;


    @PostMapping("pushUser")
    @ResponseBody
    public String pushUserInfo(User user){
        try {
            userPushService.push(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
       return "SUCCESS";
    }

    @PostMapping("pushNews")
    @ResponseBody
    public String pushNewsInfo(News news){
        try {
            newsPushService.push(news);
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
        return "SUCCESS";
    }
}
