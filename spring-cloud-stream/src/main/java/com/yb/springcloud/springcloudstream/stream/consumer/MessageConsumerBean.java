package com.yb.springcloud.springcloudstream.stream.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: yangb
 * @Description: 消息消费
 */
@Component
@EnableBinding({Sink.class})
public class MessageConsumerBean {

	@Autowired
	@Qualifier(Sink.INPUT)
	private SubscribableChannel subscribableChannel;

	@Autowired
	private Sink sink;

	@PostConstruct
	public void init() {
		subscribableChannel.subscribe((message)-> {
			System.out.println("subscribe:" + message.getPayload());
		});
	}

	@ServiceActivator(inputChannel = Sink.INPUT)
	public void onMessage(Object message) {
		System.out.println("@ServiceActivator:" + message);
	}

	@StreamListener(Sink.INPUT)
	public void onMessage(String message) {
		System.out.println("@StreamListener:" + message);
	}
}
