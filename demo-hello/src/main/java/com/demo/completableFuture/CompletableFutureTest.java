package com.demo.completableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author 杨滨
 * @date 2021年04月26日
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        testdemo();
    }


    //请用并发编程，结合生活常识，完成下面事情
    private static void testdemo() {
        CompletableFuture zhufan = CompletableFuture.runAsync(CompletableFutureTest::zhufan);
        CompletableFuture zuochai = CompletableFuture.runAsync(CompletableFutureTest::qiecai).thenRun(CompletableFutureTest::chaocai);
        CompletableFuture.allOf(zhufan, zuochai).thenRun(CompletableFutureTest::chifan);
        int i = 0;
        while (true) {
//            System.out.println(i++);
            sleep(1);
        }
    }

    private static void chifan() {
        System.out.println("吃饭5分钟-开始");
        sleep(5);
        System.out.println("吃饭5分钟-结束");
    }

    private static void chaocai() {
        System.out.println("炒菜10分钟-开始");
        sleep(10);
        System.out.println("炒菜10分钟-结束");
    }

    private static void qiecai() {
        System.out.println("切菜2分钟-开始");
        sleep(2);
        System.out.println("切菜2分钟-结束");
    }

    private static void zhufan() {
        System.out.println("煮饭20分钟-开始");
        sleep(20);
        System.out.println("煮饭20分钟-结束");
    }

    //使用BiConsumer处理两个阶段的结果
    static void thenAcceptBothExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original)
                .thenApply(String::toUpperCase)
                .thenAcceptBoth(CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                        (s1, s2) -> result.append(s1 + s2));
        System.out.println(result.toString());
    }

    //在两个阶段都执行完后运行一个 Runnable
    static void runAfterBothExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture<Void> cf = CompletableFuture.completedFuture(original).thenApply(s -> {
            sleep3();
            return s.toUpperCase();
        }).runAfterBoth(
                CompletableFuture.completedFuture(original).thenApply(s -> {
                    sleep5();
                    return s.toLowerCase();
                }),
                () -> result.append(original).append("done"));
        System.out.println(result);
        System.out.println(cf.getNow(null));
    }

    static void thenApplyExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            System.out.println(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        System.out.println(cf.getNow(null));
    }

    static void thenApplyAsyncExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            System.out.println(Thread.currentThread().isDaemon());
            randomSleep();
            return s.toUpperCase();
        });
        System.out.println(cf.getNow(null));
        System.out.println(cf.join());
    }

    private static void sleep3() {
        System.out.println("休息3分钟，喝茶");
        sleep(3);
    }

    private static void sleep5() {
        System.out.println("休息5分钟，吃饭");
        sleep(5);
    }

    private static void sleep(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void randomSleep() {
        try {
            double random = Math.random() * 10;
            System.out.println(random);
            Thread.sleep((long) random * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
