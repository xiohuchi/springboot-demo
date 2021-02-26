package com.test;

/**
 * @author yangbin
 * @date 2021年02月25日
 */
public class TestClass {
    public static void main(String[] args) {
        ClassInit init = new ClassInit();
    }
}

class ClassInit {
    public ClassInit() {
        System.out.println("A");
    }

    {
        System.out.println("B");
    }

    {
        System.out.println("C");
    }
}