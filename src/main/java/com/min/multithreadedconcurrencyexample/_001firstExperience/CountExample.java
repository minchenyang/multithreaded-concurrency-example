package com.min.multithreadedconcurrencyexample._001firstExperience;

import com.min.multithreadedconcurrencyexample.annoations.ThreadSofe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: multithreaded-concurrency-example
 * @description: concurrent testing
 * @author: mcy
 * @create: 2018-10-25 11:26
 **/

@ThreadSofe
public class CountExample {
    //同时并发的线程数
    private static int threadTotal = 200;
    //请求总和
    private static int clientTotal = 5000;
    //计数
    private static long count = 0;

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
        count++;
    }
}
