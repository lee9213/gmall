package com.lee9213.user.notify.properties;


import com.lee9213.user.constant.KafkaTopicConstant;
import com.lee9213.user.notify.listener.LuckyDrawChangeMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.stereotype.Component;

/**
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-08-25 10:14
 */
@Component("luckyDrawChangeContainerProperties")
public class LuckyDrawChangeContainerProperties extends ContainerProperties {

    public LuckyDrawChangeContainerProperties() {
        super(KafkaTopicConstant.LUCKY_DRAW_CHANCE_TOPIC);
    }

    @Autowired
    private LuckyDrawChangeMessageListener luckyDrawChangeMessageListener;


    @Override
    public void setMessageListener(Object messageListener) {
        super.setMessageListener(luckyDrawChangeMessageListener);
    }

    @Override
    public Object getMessageListener() {
        return luckyDrawChangeMessageListener;
    }
}
