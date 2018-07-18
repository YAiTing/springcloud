package com.yb.springcloud.springcloudstream.stream.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Author: yangb
 * @Description:
 */
public interface MySource {

	/**
	 * 消息来源的管道名称 "myoutput"
	 */
	String NAME = "myoutput";

	@Output(NAME)
	MessageChannel output();
}
