package com.yb.springcloud.springcloudbus.web.controller;

import com.yb.springcloud.springcloudbus.bus.event.MessageRemoteApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangb
 * @Description:
 */
@RestController
public class MessageEventController {

	@Autowired
	private ApplicationContext applicationContext;

	@GetMapping
	public MessageRemoteApplicationEvent syncEvent(@RequestParam String message) {
		String destinationService = "";
		MessageRemoteApplicationEvent event = new MessageRemoteApplicationEvent(message, applicationContext.getId(),destinationService);
		applicationContext.publishEvent(event);
		return event;
	}
}
