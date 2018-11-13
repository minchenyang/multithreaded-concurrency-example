package com.min.multithreadedconcurrencyexample.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: multithreaded-concurrency-example
 * @description:
 * @author: mcy
 * @create: 2018-10-27 18:23
 **/
@Target(ElementType.TYPE)//Describe the range is Classs
@Retention(RetentionPolicy.SOURCE)//Compile-time validity
public @interface ThreadSofe {
    String value() default "";
}
