package com.lvmama.activemq.producer.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class UserConsumer {

    @JmsListener(destination = "userServiceQueue?consumer.prefetchSize=10")
    public void receiveUser(String message){
        System.out.println("UserConsumer_1接收到的用户信息为："+ message);
    }
}
