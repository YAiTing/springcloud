package com.yb.springcloud.springcloudstream.web.comtroller;

import com.yb.springcloud.springcloudstream.stream.producer.MessageProducerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangb
 * @Description:
 */
@RestController
@RequestMapping("/message")
public class ProduceController {

	@Autowired
	private MessageProducerBean messageProducerBean;

	@GetMapping("/send")
	public Boolean send(@RequestParam String message) {
		messageProducerBean.send(message);
		return true;
	}

	@GetMapping("/send/myoutput")
	public Boolean sendToMyoutput(@RequestParam String message) {
		messageProducerBean.sendToMyoutput(message);
		return true;
	}

}
