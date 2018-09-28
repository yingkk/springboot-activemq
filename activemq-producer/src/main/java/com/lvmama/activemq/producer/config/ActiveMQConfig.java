package com.lvmama.activemq.producer.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.*;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;

@Configuration
public class ActiveMQConfig {

    @Bean
    public ActiveMQConnectionFactory targetConnectionFactory(){
        ActiveMQConnectionFactory targetMQConnectionFactory = new ActiveMQConnectionFactory();

        Properties props = new Properties();
        //设置Queue预取限制
       // props.setProperty("prefetchPolicy.queuePrefetch", "2");
        //props.setProperty("prefetchPolicy.queueBrowserPrefetch", "500");
       // props.setProperty("prefetchPolicy.durableTopicPrefetch", "100");
        //设置Topic预取限制
       // props.setProperty("prefetchPolicy.topicPrefetch", "32766");
        targetMQConnectionFactory.setProperties(props);

        targetMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        return targetMQConnectionFactory;
    }

    @Bean
    public SingleConnectionFactory connectionFactory(PooledConnectionFactory targetConnectionFactory){
        SingleConnectionFactory connectionFactory = new SingleConnectionFactory();
        connectionFactory.setTargetConnectionFactory(targetConnectionFactory);
        return connectionFactory;
    }

    @Bean
    public PooledConnectionFactory pooledConnectionFactory(ActiveMQConnectionFactory targetConnectionFactory){
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(targetConnectionFactory);
        return pooledConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(SingleConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory);
        //jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return jmsTemplate;
    }

}
