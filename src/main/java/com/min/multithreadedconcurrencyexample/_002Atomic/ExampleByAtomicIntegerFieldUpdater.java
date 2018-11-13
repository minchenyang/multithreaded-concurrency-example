package com.min.multithreadedconcurrencyexample._002Atomic;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @program: multithreaded-concurrency-example
 * @description:
 * @author: mcy
 * @create: 2018-10-28 18:28
 **/
public class ExampleByAtomicIntegerFieldUpdater {

    /**
     * 原子更新类中字段
     */

    private static AtomicIntegerFieldUpdater<ExampleByAtomicIntegerFieldUpdater> atomicIntegerFieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(ExampleByAtomicIntegerFieldUpdater.class,"count");
    @Getter
    public volatile int count = 100;//非static 并且是 volatile 描述的字段

    public static void main(String[] args) {

        ExampleByAtomicIntegerFieldUpdater ob = new ExampleByAtomicIntegerFieldUpdater();

        if(atomicIntegerFieldUpdater.compareAndSet(ob,100,120)){
            System.out.println("更新成功");
        }
        if(atomicIntegerFieldUpdater.compareAndSet(ob,100,120)){
            System.out.println("更新成功");
        }
    }
}
