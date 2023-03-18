package com.mashibing.Visibility;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * @author xcy
 * @date 2021/8/19 - 17:37
 */
public class DisruptorDemo {
	public static void handleEvent(LongEvent event,long sequence,boolean endOfBatch){
		System.out.println("Event:" + event);
	}

	public static void translate(LongEvent event, long sequence, ByteBuffer buffer) {
		event.set(buffer.getLong(0));
	}

	public static void main(String[] args) throws InterruptedException {

		int bufferSize = 1024;
		Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);

		disruptor.handleEventsWith(DisruptorDemo::handleEvent);

		disruptor.start();

		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		ByteBuffer byteBuffer = ByteBuffer.allocate(8);

		for (long i = 0; true; i++) {
			byteBuffer.putLong(0, i);
			ringBuffer.publishEvent(DisruptorDemo::translate,byteBuffer);
			Thread.sleep(1000);
		}
	}
}
