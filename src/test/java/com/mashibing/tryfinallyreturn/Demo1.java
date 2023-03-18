package com.mashibing.tryfinallyreturn;

public class Demo1 {

	public static void main(String[] args) {
		System.out.println(returnProblem());
	}

	public static int returnProblem() {
		int result = 99;

		try {
			//如果finally块中没有return返回值，那么该方法最终返回的还是try块里面的return返回值的引用
			return result;
		}catch (Exception e) {
			result++;
			return result;
		}finally {
			System.out.println("-------");
			result = result * 100;
			//return result;
		}
	}
}