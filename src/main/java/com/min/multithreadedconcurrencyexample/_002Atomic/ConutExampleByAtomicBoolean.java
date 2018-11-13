package com.min.multithreadedconcurrencyexample._002Atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: multithreaded-concurrency-example
 * @description:
 * @author: mcy
 * @create: 2018-10-28 17:48
 **/
public class ConutExampleByAtomicBoolean {
    //同时并发的线程数
    private static int threadTotal = 2000;
    //请求总和
    private static int clientTotal = 5000000;
    //计数
    private static int count = 0;
    private static AtomicLong  l = new AtomicLong(0);
    private static AtomicBoolean b = new AtomicBoolean(true);
    private static boolean b1 = true;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore =  new Semaphore(threadTotal);//允许同时运行的线程数目
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);//计算线程进入的次数
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    count++;
                    if(count%5000==0){
                        add();
                    }
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
        System.out.println(l);
        executorService.shutdown();
    }

    //保证只执行一次
    private static void add(){
        //当前值和 expert 进行比较并且更新为 updata
        if(b.compareAndSet(true,false)){
            l.incrementAndGet();
            System.out.println("add");
        }
        //b.set(true);
    }
}
