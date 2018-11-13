package com.min.multithreadedconcurrencyexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class MultithreadedConcurrencyExampleApplicationTests {
	//同时并发的线程数
	private static int threadTotal = 2000;
	//请求总和
	private static int clientTotal = 5000000;
	//计数
	private static  AtomicLong count = new AtomicLong(0);;
	private static AtomicLong l = new AtomicLong(0);
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
					long ll = count.incrementAndGet();
					if(ll%5000==0){
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
			l.incrementAndGet();
	}

}
