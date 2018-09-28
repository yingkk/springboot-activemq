package com.lvmama.activemq.producer.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class NewsConsumer2 {

    @JmsListener(destination = "newsServiceTopic")
    public  void  receiveNews(String message){
        System.out.println("NewsConsumer_2收到的新闻信息为："+message);
    }
}
