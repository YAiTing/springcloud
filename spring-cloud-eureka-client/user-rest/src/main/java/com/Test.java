package com;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yangb
 * @Description:
 */
public class Test {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.forEach(System.out::println);
		list.forEach(e -> System.out.println(e + ";"));
		new Thread(() -> System.out.println("Hello world !")).start();
	}

}
