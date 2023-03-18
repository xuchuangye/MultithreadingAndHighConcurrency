package com.mashibing;

/**
 * @author xcy
 * @date 2023/2/26 - 11:49
 */
public class OOPDemo3 {
	public static void main(String[] args) {

	}
}

interface Api {
	void print();

	void count(int num);
}


class A {

	public void call(Api api) {
		api.print();
	}

	public void back() {
		new Api() {

			@Override
			public void print() {
				System.out.println();
			}

			@Override
			public void count(int num) {

			}
		};
	}

	public void callback() {
		this.call(new Api() {
			@Override
			public void print() {

			}

			@Override
			public void count(int num) {

			}
		});
	}
}