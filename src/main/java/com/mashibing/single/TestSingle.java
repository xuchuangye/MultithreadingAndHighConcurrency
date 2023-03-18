package com.mashibing.single;

/**
 * @author xcy
 * @date 2021/8/10 - 8:43
 */
public class TestSingle {
	private static class InnerClass {
		private static final TestSingle TEST_SINGLE = new TestSingle();
	}

	public static TestSingle getInstance() {
		return InnerClass.TEST_SINGLE;
	}

	public static void main(String[] args) {
		TestSingle instance1 = TestSingle.getInstance();
		TestSingle instance2 = TestSingle.getInstance();
		System.out.println(instance1 == instance2);
	}
}
