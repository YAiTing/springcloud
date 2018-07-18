package com.yb.springcloud.springcloudhystrix.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

/**
 * HystrixProperty配置信息地址 https://github.com/Netflix/Hystrix/wiki/Configuration
 * @Author: yangb
 * @Description:
 */
@RestController
public class HystrixDemoController {


	@GetMapping("hello-world")
	@HystrixCommand(fallbackMethod = "errorContent",
			commandProperties = {
				@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
			}
	)
	public String helloWorld() throws InterruptedException {
		int value = ThreadLocalRandom.current().nextInt(200);
		System.out.println("helloWorld() costs" + value + " ms.");
		Thread.sleep(value);
		return "hello-world";
	}

	public String errorContent() {
		return "hystrix-falut";
	}

	@GetMapping("hello-world2")
	public String helloWorld2() throws InterruptedException {
		return new HelloWordCommond().execute();
	}

	private class HelloWordCommond extends com.netflix.hystrix.HystrixCommand<String> {

		protected HelloWordCommond() {
			super(HystrixCommandGroupKey.Factory.asKey("HelloWorld"),100);
		}

		@Override
		protected String run() throws Exception {
			int value = ThreadLocalRandom.current().nextInt(200);
			System.out.println("helloWorld2() costs" + value + " ms.");
			Thread.sleep(value);
			return "hello-world2-hystrix";
		}

		@Override
		protected String getFallback() {
			return HystrixDemoController.this.errorContent();
		}
	}
}
