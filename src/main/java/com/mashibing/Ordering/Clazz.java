package com.mashibing.Ordering;

/**
 * @author xcy
 * @date 2021/8/20 - 10:06
 */

/**
 * IDEA View -> Show Bytecode With Jclasslib -> method -> main -> code
 * 0 new #3 <com.mashibing.Ordering/Clazz>这句话表示Clazz开始申请内存空间，有一个属性m，并且属性m的值为默认值0，因为此时处于对象的半初始化状态
 *
 * 4 invokespecial #4 <com.mashibing.Ordering/Clazz.< init > : ()V>表示调用构造方法，调用构造方法之后m的值修改为初始值，初始化完成
 *
 * 7 astore_1表示变量clazz和Clazz申请的内存建立关联
 */
public class Clazz {
	int m = 8;
	public static void main(String[] args) {
		Clazz clazz = new Clazz();
	}
}
