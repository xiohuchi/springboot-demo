package com.test;

/**
 * @author yangbin
 * @date 2021年02月25日
 */
public class StackOverFlowTest {
    private int val = 0;

    public void test() throws InterruptedException {
        val++;
        Thread.sleep(1);
        System.out.println(val);

        test();
    }

    public static void main(String[] args) throws InterruptedException {
        StackOverFlowTest test = new StackOverFlowTest();
        test.test();
    }
}