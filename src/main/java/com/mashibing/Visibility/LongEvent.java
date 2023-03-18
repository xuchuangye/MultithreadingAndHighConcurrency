package com.mashibing.Visibility;


/**
 * @author xcy
 * @date 2021/8/17 - 15:09
 */
public class LongEvent {
	private long value;

	public LongEvent() {

	}

	public void set(long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "LongEvent{" +
				"value=" + value +
				'}';
	}
}
