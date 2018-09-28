package com.lvmama.activemq.producer.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;



@Component
public class NewsConsumer {

    @JmsListener(destination = "newsServiceTopic")
    public  void  receiveNews(String message){
        System.out.println("NewsConsumer_1收到的新闻信息为："+message);
    }
}
