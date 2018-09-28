package com.lvmama.activemq.producer.service.impl;


import com.alibaba.fastjson.JSON;
import com.lvmama.activemq.api.entity.User;
import com.lvmama.activemq.producer.service.PushService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;


@Service("userPushService")
public class UserPushServiceImpl implements PushService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void push(Object message) {

        Destination destination = new ActiveMQQueue("userServiceQueue");
        User user = (User) message;
        jmsMessagingTemplate.convertAndSend(destination,JSON.toJSONString(user));

    }
}
