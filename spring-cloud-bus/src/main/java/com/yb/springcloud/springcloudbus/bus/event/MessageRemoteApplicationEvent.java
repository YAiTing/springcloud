package com.yb.springcloud.springcloudbus.bus.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * @Author: yangb
 * @Description:  自定义远程事件
 */
public class MessageRemoteApplicationEvent extends RemoteApplicationEvent {

	public MessageRemoteApplicationEvent() {
		//为序列化
	}

	public MessageRemoteApplicationEvent(Object source, String originService, String destinationService) {
		super(source, originService, destinationService);
	}

	public String getMessage() {
		return (String)getSource();
	}
}
