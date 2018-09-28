package com.lvmama.activemq.producer.service.impl;


import com.alibaba.fastjson.JSON;
import com.lvmama.activemq.api.entity.News;
import com.lvmama.activemq.producer.service.PushService;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

@Service("newsPushService")
public class NewsPushServiceImpl implements PushService{

        @Autowired
        private JmsMessagingTemplate  jmsMessagingTemplate;


        public void push(Object message) {
         Destination destination = new ActiveMQTopic("newsServiceTopic");
               News news = (News) message;
              jmsMessagingTemplate.convertAndSend(destination,JSON.toJSONString(news));
        }

}



