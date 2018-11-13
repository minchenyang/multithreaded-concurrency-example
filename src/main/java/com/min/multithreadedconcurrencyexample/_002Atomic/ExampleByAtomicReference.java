package com.min.multithreadedconcurrencyexample._002Atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: multithreaded-concurrency-example
 * @description:
 * @author: mcy
 * @create: 2018-10-28 18:22
 **/
public class ExampleByAtomicReference {

    //原子性的更新引用
    private static AtomicReference<Integer> count  = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0,2);
        count.compareAndSet(0,1);
        count.compareAndSet(1,3);
        count.compareAndSet(2,4);
        count.compareAndSet(3,5);

        System.out.println(count.get());
    }

}
