package com.test;

/**
 * 类加载过程
 *
 * @author yangbin
 * @date 2021年02月25日
 */
public class Test_1 {
    public static void main(String[] args) {
        Test_1_B[] b = new Test_1_B[100];
    }
}

class Test_1_A {
    static {
        System.out.println("A Static Block");
    }
}

class Test_1_B extends Test_1_A {
    static {
        System.out.println("B Static Block");
    }
}

/*


 */