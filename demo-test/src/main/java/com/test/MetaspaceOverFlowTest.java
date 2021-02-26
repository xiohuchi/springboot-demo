package com.test;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author yangbin
 * @date 2021年02月25日
 */
public class MetaspaceOverFlowTest {
    /**
     * 通过CGLIB模拟向元空间写入数据
     */
    public static void main(String[] args) {
        while (true) {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            Enhancer enhancer = new Enhancer();

            enhancer.setSuperclass(MetaspaceOverFlowTest.class);
            enhancer.setUseCache(false);//cache如何理解？设置为true、false结果会有何不同？
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });

            System.out.println("running...");

            enhancer.create();
        }
    }
}
