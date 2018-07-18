package com.yb.springcloud.springcloudstream.stream.producer;

import com.yb.springcloud.springcloudstream.stream.messaging.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @Author: yangb
 * @Description:
 */
@Component
@EnableBinding({Source.class, MySource.class})
public class MessageProducerBean {

	@Autowired
	@Qualifier(Source.OUTPUT)
	private MessageChannel messageChannel;

	@Autowired
	private Source source;

	@Autowired
	@Qualifier(MySource.NAME)
	private MessageChannel myMessageChannel;

	public void send(String message) {
		// 通过消息管道发送消息
		messageChannel.send(MessageBuilder.withPayload(message).build());
//		source.output().send(MessageBuilder.withPayload(message).build());
	}

	public void sendToMyoutput(String message) {
		// 通过消息管道发送消息
		myMessageChannel.send(MessageBuilder.withPayload(message).build());
	}
}
