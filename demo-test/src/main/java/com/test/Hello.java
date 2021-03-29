package com.test;

import java.util.Stack;

/**
 * @author yangbin
 * @date 2021年02月25日
 */
public class Hello {
    public static void main(String[] args) {
        Stack<String> strings = new Stack<>();
        strings.push("1");
        strings.push("2");
        strings.push("3");
        strings.push("4");
        strings.push("5");
        strings.push("6");
        String pop = strings.pop();
        System.out.println(pop);
    }
}
