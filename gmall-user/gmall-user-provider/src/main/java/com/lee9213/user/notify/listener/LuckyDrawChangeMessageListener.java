package com.lee9213.user.notify.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-25 10:10
 */

@Component("luckyDrawChangeMessageListener")
public class LuckyDrawChangeMessageListener implements MessageListener<Integer, String> {

    @Override
    public void onMessage(ConsumerRecord<Integer, String> data) {
        System.out.println("==================");
        System.out.println(data.value());
        System.out.println("==================");
    }
}
