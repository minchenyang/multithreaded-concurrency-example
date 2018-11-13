package com.min.multithreadedconcurrencyexample._002Atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;

/**
 * @program: multithreaded-concurrency-example
 * @description:
 * @author: mcy
 * @create: 2018-10-28 17:36
 **/
public class ConutExampleByLongAddr {
    //同时并发的线程数
    private static int threadTotal = 200;
    //请求总和
    private static int clientTotal = 5000;
    //计数
    private static LongAdder count = new LongAdder();//1.8新加  默认0

    /**
     * 和Atomiclong 相比较
     * 和Atomiclong 是 do while 循环获取主内存的值 若并发量很大的话比较影响性能。
     * LongAdder 别的方法更新和获取值。 并发量大的话 影响比较小
     * 低并发用 AtomicLong 高并发用LongAdder
     */

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore =  new Semaphore(threadTotal);//允许同时运行的线程数目
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);//计算线程进入的次数
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        try {
            countDownLatch.await();//没达到次数 进行睡眠
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
        executorService.shutdown();
    }

    private static void add(){
        count.increment();
    }
}
