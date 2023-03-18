package com.mashibing.Multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xcy
 * @date 2021/8/14 - 9:36
 */
public class SynchronizedListTest {
	public static void main(String[] args) {
		List<String> strList = new ArrayList<>();
		List<String> strSync = Collections.synchronizedList(strList);
	}
}
