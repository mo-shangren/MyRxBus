package com.killr.rxbus.annotation;

import com.killr.rxbus.Bus;
import com.killr.rxbus.finder.AnnotatedFinder;
import com.killr.rxbus.thread.EventThread;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a method as an instance producer, as used by {@link AnnotatedFinder} and {@link Bus}.
 * <p/>
 * Bus infers the instance type from the annotated method's return type. Producer methods may return null when there is
 * no appropriate value to share. The calling {@link Bus} ignores such returns and posts nothing.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Produce {
    Tag[] tags() default {};

    EventThread thread() default EventThread.MAIN_THREAD;
}
