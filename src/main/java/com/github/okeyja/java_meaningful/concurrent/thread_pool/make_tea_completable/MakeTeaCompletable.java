package com.github.okeyja.java_meaningful.concurrent.thread_pool.make_tea_completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * // 如果所有 CompletableFuture 共享一个线程池，那么一旦有任务执行一些很慢的 I/O 操作，
 * // 就会导致线程池中所有线程都阻塞在 I/O 操作上，从而造成线程饥饿，进而影响整个系统的性能。
 * // 所以，强烈建议你要根据不同的业务类型创建不同的线程池，以避免互相干扰。
 * <p>
 * // 使用默认线程池
 * static CompletableFuture&lt;Void&gt;
 * runAsync(Runnable runnable)
 * static &lt;U&gt; CompletableFuture&lt;U&gt;
 * supplyAsync(Supplier&lt;U&gt; supplier)
 * // 可以指定线程池
 * static CompletableFuture&lt;Void&gt;
 * runAsync(Runnable runnable, Executor executor)
 * static &lt;U&gt; CompletableFuture&lt;U&gt;
 * supplyAsync(Supplier&lt;U&gt; supplier, Executor executor)
 */
public class MakeTeaCompletable {
    public static void main(String[] args) {
        // 任务1：洗水壶->烧开水，没有返回值
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1:洗水壶...");
            sleep(1);

            System.out.println("T1:烧开水...");
            sleep(15);
        });
        // 任务2：洗茶壶->洗茶杯->拿茶叶，有返回值
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2:洗茶壶...");
            sleep(1);

            System.out.println("T2:洗茶杯...");
            sleep(2);

            System.out.println("T2:拿茶叶...");
            sleep(1);
            return "龙井";
        });
        // 任务3：任务1和任务2完成后执行：泡茶
        // 串行关系：thenApply、thenAccept、thenRun 和 thenCompose
        // thenApply 支持返回值
        // thenAccept 不支持返回值
        // thenRun 不支持返回值 Runnable
        // thenCompose 支持返回值 创建新的子流程，结果同 thenApply
        /*
            CompletionStage<R> thenApply(fn);
            CompletionStage<R> thenApplyAsync(fn);
            CompletionStage<Void> thenAccept(consumer);
            CompletionStage<Void> thenAcceptAsync(consumer);
            CompletionStage<Void> thenRun(action);
            CompletionStage<Void> thenRunAsync(action);
            CompletionStage<R> thenCompose(fn);
            CompletionStage<R> thenComposeAsync(fn);
         */
        // AND 汇聚关系
        /*
            CompletionStage<R> thenCombine(other, fn);
            CompletionStage<R> thenCombineAsync(other, fn);
            CompletionStage<Void> thenAcceptBoth(other, consumer);
            CompletionStage<Void> thenAcceptBothAsync(other, consumer);
            CompletionStage<Void> runAfterBoth(other, action);
            CompletionStage<Void> runAfterBothAsync(other, action);
         */
        // OR 汇聚关系
        /*
            CompletionStage applyToEither(other, fn);
            CompletionStage applyToEitherAsync(other, fn);
            CompletionStage acceptEither(other, consumer);
            CompletionStage acceptEitherAsync(other, consumer);
            CompletionStage runAfterEither(other, action);
            CompletionStage runAfterEitherAsync(other, action);
         */
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) -> {
            System.out.println("T1:拿到茶叶:" + tf);
            System.out.println("T1:泡茶...");
            return "上茶:" + tf;
        });
        // 等待任务3执行结果
        System.out.println(f3.join());
    }

    private static void sleep(int t) {
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException ignored) {
        }
    }
}
