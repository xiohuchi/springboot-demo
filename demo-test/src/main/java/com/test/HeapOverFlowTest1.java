package com.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangbin
 * @date 2021年02月25日
 */
public class HeapOverFlowTest1 {
    public static void main(String[] args) {
        List<HeapOverFlowTest1> objs = new ArrayList<>();

        for (; ; ) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            objs.add(new HeapOverFlowTest1());
        }

    }
}
