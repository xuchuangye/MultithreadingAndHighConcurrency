package com.mashibing;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xcy
 * @date 2022/10/15 - 9:03
 */
public class Test_ConcurrentHashMap {
	public static void main(String[] args) {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

		map.computeIfAbsent("aaa", key -> {
			System.out.println(Thread.currentThread().getName());
			map.computeIfAbsent("aaa", key2 -> {
				System.out.println(Thread.currentThread().getName());
				return "bbb";
			});
			return "bbb";
		});
	}
}
