面试题

WithoutVolatile.java
实现一个容器，提供两个方法add、size
写出两个线程：线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数为5时，线程2给出提示并结束

WithoutVolatileAndVolatile.java
使用关键字volatile修饰lists集合，可以暂时性解决问题。但是将sleep去除之后，仍然会出现线程2获取不到值，无法break，因此仍然会进入死循环
volatile List<Object> lists = new ArrayList<>();
//即使是使用同步容器synchronizedList，仍然会出现线程2获取不到线程1的值，无法break，进入死循环
//volatile List<Object> lists = Collections.synchronizedList(new LinkedList<>());

注意事项：
//volatile关键字，没有绝对的把握就不要使用
//volatile关键字即使是使用，也要尽量修饰简单的类型
//volatile观察不到引用类型对象所在的堆空间内部是否修改了值

WithoutVolatile2.java
//使用Object类中的wait、notify方法，但是还是会有问题，因为线程2阻塞之后始终得不到锁，所以最后的结果是线程1执行完，线程2才拿到这把锁继续执行
//注意，当该线程wait之后，只是被notify之后是没有用的，因为只要别的线程没有释放锁，该线程就得不到锁，因此无法继续执行


WithoutVolatileAndWaitNotify.java
//当线程2不满足条件时，调用wait方法之后就一直处于阻塞状态
//而当线程2满足条件之后，线程1除了调用notify方法要唤醒线程2，还要释放锁，让线程2得到这把锁，然后线程2继续执行
//而当线程2执行完毕之后，要唤醒线程1，然后线程1继续执行，直到线程1结束


CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行。
使用一个计数器进行实现。计数器初始值为线程的数量。
当每一个线程完成自己任务后，计数器的值就会减一。
当计数器的值为0时，表示所有的线程都已经完成一些任务，然后在CountDownLatch上等待的线程就可以恢复执行接下来的任务。

WithoutVolatileAndCountDownLatch.java
//使用倒数计时器CountDownLatch类中的方法await、countdown

WithoutVolatileAndLockSupport.java
//使用LockSupport线程工具类

WithoutVolatileAndSemaphore.java
//使用同步