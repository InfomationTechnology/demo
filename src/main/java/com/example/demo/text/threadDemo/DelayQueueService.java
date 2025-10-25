package com.example.demo.text.threadDemo;

import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * 调用addTask方法将任务丢到延迟队列中，主要参数（延迟时间、任务信息、回调【任务到期后会进行回调】）
 * 使用到了java中的延迟队列DelayQueue来存放延迟任务
 * 下面的构造方法会自动调用一个start方法，start方法中会自动启动一个线程，线程轮询从延迟队列中拉取到期的任务，然后丢到线程池executorService.submit中进行处理，会自动调用创建延迟任务中指定的回调函数
 * main方法中有使用步骤
 * @date 2025/7/25 15:15
 */
public class DelayQueueService<T> {
    Logger logger = Logger.getLogger(DelayQueueService.class.getName());
    //延迟队列名称
    private String delayQueueName;
    private DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();
    //处理队列中任务的线程池
    private ExecutorService executorService;
    public DelayQueueService(String delayQueueName) {
        this(delayQueueName, Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4));
    }
    public DelayQueueService(String delayQueueName, ExecutorService executorService) {
        this.delayQueueName = delayQueueName;
        this.executorService = executorService;
        //启动队列消费
        this.start();
    }
    /**
     * 添加任务
     *
     * @param delayedTimeUnit 延迟时间单位
     * @param delayedTime     延迟时间
     * @param task            任务
     * @param consumer        任务消费者（到期了会回调）
     */
    public void addTask(TimeUnit delayedTimeUnit, long delayedTime, T task, Consumer<T> consumer) {
        this.delayQueue.offer(new DelayedTask(delayedTimeUnit, delayedTime, task, consumer));
    }
    private void start() {
        //轮询从延迟队列中拉取任务，然后调用线程池进行处理
        Thread pollThread = new Thread(() -> {
            while (true) {
                try {
                    DelayedTask delayedTask = this.delayQueue.poll(100, TimeUnit.MILLISECONDS);
                    if (this.executorService.isShutdown()) {
                        break;
                    }
                    if (delayedTask != null) {
                        executorService.submit(() -> {
                            delayedTask.consumer.accept(delayedTask.task);
                        });
                    }
                } catch (InterruptedException e) {
                    logger.warning(e.getMessage());
                }
            }
        });
        pollThread.setDaemon(Thread.currentThread().isDaemon());
        pollThread.setName(this.getClass().getName() + "-pollThread-" + this.delayQueueName);
        pollThread.start();
    }
    public void close() {
        if (!this.executorService.isShutdown()) {
            this.executorService.shutdown();
        }
    }
    public class DelayedTask implements Delayed {
        //延迟时间单位
        private TimeUnit delayedTimeUnit;
        //延迟时间
        private long delayedTime;
        //到期时间(毫秒)
        private long endTime;
        //延迟任务信息
        private T task;
        //消费者
        private Consumer<T> consumer;
        public DelayedTask(TimeUnit delayedTimeUnit, long delayedTime, T task, Consumer<T> consumer) {
            this.delayedTimeUnit = delayedTimeUnit;
            this.delayedTime = delayedTime;
            this.task = task;
            this.endTime = System.currentTimeMillis() + delayedTimeUnit.toMillis(delayedTime);
            this.consumer = consumer;
        }
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.endTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }
        @Override
        public int compareTo(Delayed o) {
            DelayedTask task = (DelayedTask) o;
            return Long.compare(this.endTime, task.endTime);
        }
    }
    public static void main(String[] args) {
        //创建一个延迟队列：用来对超过支付日期的订单进行关闭
        String delayQueueName = "orderCloseDelayQueue";
        //1、创建延迟队列
        DelayQueueService<String> orderCloseDelayQueue = new DelayQueueService<String>(delayQueueName);
        for (int i = 1; i <= 10; i++) {
            //2、调用addTask将延迟任务加入延迟队列
            orderCloseDelayQueue.addTask(TimeUnit.SECONDS, i, "订单" + i, new Consumer<String>() {
                @Override
                public void accept(String s) {
                    System.out.println(System.currentTimeMillis() + "," + Thread.currentThread() + ",关闭订单:" + s);
                }
            });
        }
        //3、系统关闭的时候，调用延迟队列的close方法
        //orderCloseDelayQueue.close();
    }
}
