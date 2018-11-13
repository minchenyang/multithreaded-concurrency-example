package com.min.multithreadedconcurrencyexample._002Atomic;

import com.min.multithreadedconcurrencyexample.annoations.ThreadSofe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: multithreaded-concurrency-example
 * @description:
 * @author: mcy
 * @create: 2018-10-28 17:20
 **/
@ThreadSofe
public class ConutExampleByAtomicInteger {
    //同时并发的线程数
    private static int threadTotal = 200;
    //请求总和
    private static int clientTotal = 5000;
    //计数
    private static AtomicInteger count = new AtomicInteger(0);

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
        count.incrementAndGet();
        //调用方法 增加前先判断主内存中的值，若一致则增加(并且覆盖底层的值），若不一致则从新获取主内存的值
        //count.getAndIncrement();
    }
}
