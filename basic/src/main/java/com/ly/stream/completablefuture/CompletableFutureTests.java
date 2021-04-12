package com.ly.stream.completablefuture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * liyang 2021-04-12
 *
 * 组合式异步编程
 * Completable Future 可以手工完成，应用场景可以是某个 api 服务提供方挂掉，可以服务降级，使用缓存数据手动完成任务，保证服务可用
 *
 * 参考资料：
 * https://juejin.cn/post/6844903594165026829
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1306581182447650
 *
 */
public class CompletableFutureTests {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /******************************* Part 1  ********************************/
        /******************************* Round 1 ********************************/
        /**
         * 可以使用如下无参构造函数简单的创建 CompletableFuture
         */
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        /**
         * 手动完成一个 Future
         */
        completableFuture.complete("Hello completable future!");

        /**
         * 获取结果，由于没有实际被某个线程运行，所以会一直阻塞
         */
        String result = completableFuture.get();

        /**
         * 打印结果
         */
        System.out.println("Future's Result: " + result); // Future's Result: Hello completable future!


        /******************************* Round 2 ********************************/
        /**
         * 使用 runAsync() 运行异步计算
         *
         * 如果你想异步的运行一个后台任务并且不想改任务返回任务东西，这时候可以使用 CompletableFuture.runAsync()方法，
         * 它持有一个Runnable 对象，并返回 CompletableFuture<Void>。
         *
         * ForkJoinPool.commonPool-worker-1: I'll run in a separate thread rather than the main thread.
         */
        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": I'll run in a separate thread rather than the main thread.");
        });
        completableFuture2.get(); // ForkJoinPool.commonPool-worker-1: I'll run in a separate thread rather than the main thread.


        /******************************* Round 3 ********************************/
        /**
         * 使用 supplyAsync() 运行一个异步任务并且返回结果 当任务不需要返回任何东西的时候， CompletableFuture.runAsync() 非常有用。但是如果你的后台任务需要返回一些结果应该要怎么样？
         * CompletableFuture.supplyAsync() 就是你的选择。它持有supplier<T> 并且返回CompletableFuture<T>，T 是通过调用 传入的supplier取得的值的类型。
         */
        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName() + ": Result of the asynchronous computation.";
        });
        String result3 = completableFuture3.get(); // Block and get the result of the Future
        System.out.println(result3); // ForkJoinPool.commonPool-worker-1: Result of the asynchronous computation.


        /******************************* Round 4 ********************************/
        /**
         * 一个关于Executor 和Thread Pool笔记
         * 你可能想知道，我们知道 runAsync() 和 supplyAsync() 方法在单独的线程中执行他们的任务。但是我们不会永远只创建一个线程。
         * CompletableFuture 可以从全局的 ForkJoinPool.commonPool() 获得一个线程中执行这些任务。
         * 但是你也可以创建一个线程池并传给 runAsync() 和 supplyAsync() 方法来让他们从线程池中获取一个线程执行它们的任务。
         * CompletableFuture API 的所有方法都有两个变体，一个接受 Executor 作为参数，另一个不需要。
         *
         * 创建一个线程池，并传递给其中一个方法：
         */
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<String> completableFuture4 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getName() + ": Result of the asynchronous computation";
        }, executorService);
        String result4 = completableFuture4.get();    // Block and get the result of the Future
        System.out.println(result4); // pool-1-thread-1: Result of the asynchronous computation
        executorService.shutdown();


        /******************************* Round 5 ********************************/
        /**
         * CompletableFuture.get() 方法是阻塞的。它会一直等到Future完成并且在完成后返回结果。
         * 但是，这是我们想要的吗？对于构建异步系统，我们应该附上一个回调给 CompletableFuture，当 Future 完成的时候，自动的获取结果。
         * 如果我们不想等待结果返回，我们可以把需要等待 Future 完成执行的逻辑写入到回调函数中。
         * 可以使用 thenApply(), thenAccept() 和 thenRun() 方法附上一个回调给 CompletableFuture。
         *
         * 1. thenApply() 可以使用 thenApply() 处理和改变 CompletableFuture 的结果。
         *    持有一个 Function<R,T> 作为参数。Function<R,T> 是一个简单的函数式接口，
         *    接受一个T类型的参数，产出一个R类型的结果。
         */
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "liyang";
        });
        // Attach a callback to the Future using thenApply()
        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> "Hello " + name);
        System.out.println(greetingFuture.get()); // Hello liyang
        // or attach a callback to the Future using thenAccept()
        whatsYourNameFuture.thenAccept(name -> System.out.println("Hello => " + name));


        /******************************* Round 6 ********************************/
        /**
         * 你也可以通过附加一系列的 thenApply() 在回调方法
         * 在 CompletableFuture 写一个连续的转换。
         * 这样的话，结果中的一个 thenApply 方法就会传递给该系列的另外一个 thenApply 方法。
         */
        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "liyang";
        }).thenApply(name -> "Hello " + name).thenApply(greeting -> greeting + ", Welcome to the liyang's Blog");
        System.out.println(welcomeText.get()); // Hello liyang, Welcome to the liyang's Blog


        /******************************* Round 7 ********************************/
        /**
         * 2. thenAccept() 和 thenRun()
         *    如果你不想从你的回调函数中返回任何东西，仅仅想在 Future 完成后运行一些代码片段，你可以使用 thenAccept() 和 thenRun()方法，
         *    这些方法经常在调用链的最末端的最后一个回调函数中使用。
         *
         *    CompletableFuture.thenAccept() 持有一个 Consumer<T>，返回一个 CompletableFuture<Void>，
         *    它可以访问CompletableFuture的结果。
         *
         *    Hello, first supplier => then consumer!
         */
        CompletableFuture.supplyAsync(() -> "Hello, first supplier ").thenAccept(process -> System.out.println(process + "=> then consumer!"));


        /******************************* Round 8 ********************************/
        /**
         * thenAccept() 可以访问 CompletableFuture 的结果，
         * 但 thenRun() 不能访 Future 的结果，它持有一个 Runnable 返回 CompletableFuture
         *
         * Hello, this is the newly runnable task!
         */
        CompletableFuture.supplyAsync(() -> "result from task").thenRun(() -> {
            System.out.println("Hello, this is the newly runnable task!");
        });


        /******************************* Round 9 ********************************/
        /**
         * 异步回调方法的笔记
         * CompletableFuture提供的所有回调方法都有两个变体：
         * thenApply() variants <U> CompletableFuture<U>
         * thenApply(Function<? super T,? extends U> fn) <U> CompletableFuture<U>
         * thenApplyAsync(Function<? super T,? extends U> fn) <U> CompletableFuture<U>
         * thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
         * 这些异步回调变体通过在独立的线程中执行回调任务帮助你进一步执行并行计算。
         * 以下示例：
         */
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return Thread.currentThread().getName() + ": Some Result";
        }).thenApply(lastResult -> {
            /**
             * Executed in the same thread where the supplyAsync() task is executed
             * or in the main thread If the supplyAsync() task completes immediately (Remove sleep() call to verify)
             */
            return lastResult + " => " + Thread.currentThread().getName() + ": Processed Result";
        });
        System.out.println(stringCompletableFuture.get()); // ForkJoinPool.commonPool-worker-1: Some Result => ForkJoinPool.commonPool-worker-1: Processed Result


        /******************************* Round 10 ********************************/
        /**
         * 在以上示例中，在thenApply() 中的任务和在 supplyAsync() 中的任务执行在相同的线程中。
         * 任何 supplyAsync() 立即执行完成,那就是执行在主线程中（尝试删除sleep测试下）。
         * 为了控制执行回调任务的线程，你可以使用异步回调。如果你使用 thenApplyAsync() 回调，将从 ForkJoinPool.commonPool() 获取不同的线程执行。
         *
         * ForkJoinPool.commonPool-worker-1: Some Result => ForkJoinPool.commonPool-worker-1: Processed Result
         */
        CompletableFuture<String> stringCompletableFuture1 = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                    return Thread.currentThread().getName() + ": Some Result";
                })
                // Maybe executed in a different thread from ForkJoinPool.commonPool()
                .thenApplyAsync(lastResult -> lastResult + " => " + Thread.currentThread().getName() + ": Processed Result");
        System.out.println(stringCompletableFuture1.get());


        /******************************* Round 11 ********************************/
        /**
         * 此外，如果你传入一个Executor到thenApplyAsync()回调中，任务将从Executor线程池获取一个线程执行。
         *
         * ForkJoinPool.commonPool-worker-2: Some Result => pool-2-thread-1: Processed Result
         */
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<String> stringCompletableFuture2 = CompletableFuture
                .supplyAsync(() -> Thread.currentThread().getName() + ": Some Result")
                // Executed in a thread obtained from the executor
                .thenApplyAsync(lastResult -> lastResult + " => " + Thread.currentThread().getName() + ": Processed Result", executor);
        System.out.println(stringCompletableFuture2.get());
        executor.shutdown();


        /******************************* Round 12 ********************************/
        /**
         * 组合两个CompletableFuture
         * 1. 使用 thenCompose() 组合两个独立的future
         *    假设你想从一个远程API中获取一个用户的详细信息，一旦用户信息可用，你想从另外一个服务中获取他的贷方。
         *    考虑下以下两个方法 getUserDetail() 和 getCreditRating() 的实现：
         */
        // 老方式，获取顶层 future 的结果需要两次 get()
        int userId = 1;
        CompletableFuture<CompletableFuture<String>> completableFutureCompletableFuture = getUserDetail(userId).thenApply(user -> getCreditRating(user));
        System.out.println(completableFutureCompletableFuture.get().get()); // [1, liyang, S]
        // 新方式，获取顶层 future 的结果需要一次 get()
        int userId1 = 2;
        CompletableFuture<String> completableFuture1 = getUserDetail(userId1).thenCompose(user -> getCreditRating(user));
        System.out.println(completableFuture1.get()); // [1, liyang, S]


        /******************************* Round 13 ********************************/
        /**
         * 2. 使用 thenCombine() 组合两个独立的 future
         *    thenCompose() 被用于当一个 future 依赖另外一个 future 的时候用来组合两个 future。
         *    thenCombine() 被用来当两个独立的 Future 都完成的时候，用来做一些事情。
         */
        System.out.println("Retrieving weight.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 88.0;
        });

        System.out.println("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 191.8;
        });

        /**
         * weightInKgFuture 和 heightInCmFuture 都完成的时候进行计算并返回一个 future => combinedFuture
         */
        System.out.println("Calculating BMI.");
        CompletableFuture<Double> combinedFuture = weightInKgFuture
                .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
                    Double heightInMeter = heightInCm / 100;
                    return weightInKg / (heightInMeter * heightInMeter);
                });

        System.out.println("Your BMI is - " + combinedFuture.get());


        /******************************* Part 2   ********************************/
        /******************************* Round 14 ********************************/
        /**
         * Part 2 部分是一个整体
         *
         * 组合多个CompletableFuture
         * 我们使用 thenCompose() 和 thenCombine() 把两个 CompletableFuture 组合在一起。
         * 现在如果你想组合任意数量的 CompletableFuture，应该怎么做？
         * 我们可以使用以下两个方法组合任意数量的 CompletableFuture。
         *
         * static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
         * static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)
         *
         * 1. CompletableFuture.allOf() CompletableFuture.allOf
         *    的使用场景是当你一个列表的独立 future，并且你想在它们都完成后并行的做一些事情。
         *
         */
        // A array of 3 web page links
        String[] arrayLinks = {"https://juejin.cn/post/6844903594165026829", "www.google.com", "www.baidu.com"};
        List<String> webPageLinks = Arrays.asList(arrayLinks);

        // Download contents of all the web pages asynchronously
        List<CompletableFuture<String>> pageContentFutures = webPageLinks.stream()
                .map(webPageLink -> downloadWebPage(webPageLink))
                .collect(Collectors.toList());

        /**
         * 所有的页面已经下载完毕，你想计算包含关键字 CompletableFuture 页面的数量。
         * 可以使用 CompletableFuture.allOf() 达成目的。
         */
        // Create a combined Future using allOf()
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                pageContentFutures.toArray(new CompletableFuture[pageContentFutures.size()])
        );

        /**
         * 使用 CompletableFuture.allOf() 的问题是它返回 CompletableFuture。
         * 但是我们可以通过写一些额外的代码来获取所有封装的 CompletableFuture 结果。
         */
        // Obtain the result
        // When all the Futures are completed, call `future.join()` to get their results and collect the results in a list -
        CompletableFuture<List<String>> allPageContentsFuture = allFutures.thenApply(v -> pageContentFutures.stream()
                .map(pageContentFuture -> pageContentFuture.join())
                .collect(Collectors.toList()));
        allPageContentsFuture.get().stream().forEach(System.out::println);

        /**
         * 花一些时间理解下以上代码片段。当所有future完成的时候，我们调用了future.join()，因此我们不会在任何地方阻塞。
         * join()方法和get()方法非常类似，这唯一不同的地方是如果最顶层的CompletableFuture完成的时候发生了异常，它会抛出一个未经检查的异常。
         * 现在让我们计算包含关键字页面的数量。
         */
        // Count the number of web pages having the "CompletableFuture" keyword.
        CompletableFuture<Long> countFuture = allPageContentsFuture.thenApply(pageContents -> {
            System.out.println(Thread.currentThread().getName()); // main
            return pageContents.stream()
                    .filter(pageContent -> pageContent.contains("CompletableFuture"))
                    .count();
        });
        // Number of Web Pages having CompletableFuture keyword - 3
        System.out.println("Number of Web Pages having CompletableFuture keyword - " + countFuture.get());


        /******************************* Part 3   ********************************/
        /******************************* Round 15 ********************************/
        /**
         * 2. CompletableFuture.anyOf()
         *    CompletableFuture.anyOf() 和其名字介绍的一样，当任何一个CompletableFuture完成的时候【相同的结果类型】，
         *    返回一个新的CompletableFuture。以下示例：
         *
         * 在以下示例中，当三个中的任何一个 CompletableFuture 完成， anyOfFuture 就会完成。
         * 因为 future2 的休眠时间最少，因此她最先完成，最终的结果将是 future2 的结果。
         *
         * CompletableFuture.anyOf() 传入一个 Future 可变参数，返回 CompletableFuture。
         * CompletableFuture.anyOf() 的问题是如果你的 CompletableFuture 返回的结果是不同类型的，
         * 这时候你将会不知道你最终 CompletableFuture 是什么类型。
         */
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 2";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 3";
        });

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2, future3);

        System.out.println(anyOfFuture.get()); // Result of Future 2


        /******************************* Part 3   ********************************/
        /******************************* Round 15 ********************************/
        /**
         * CompletableFuture 异常处理
         * 我们探寻了怎样创建CompletableFuture，转换它们，并组合多个CompletableFuture。现在让我们弄明白当发生错误的时候我们应该怎么做。
         *
         * 首先让我们明白在一个回调链中错误是怎么传递的。思考下以下回调链：
         * 如果在原始的 supplyAsync() 任务中发生一个错误，这时候没有任何 thenApply 会被调用并且 future 将以一个异常结束。
         * 如果在第一个 thenApply 发生错误，这时候第二个和第三个将不会被调用，同样的，future 将以异常结束。
         */
        CompletableFuture.supplyAsync(() -> {
            // Code which might throw an exception
            return "Some result";
        }).thenApply(res -> {
            // do something with the result
            return "processed result";
        }).thenApply(res -> {
            // do something with the result
            return "result after further processing";
        }).thenAccept(res -> {
            // do something with the final result
        });

        /**
         * 1. 使用 exceptionally() 回调处理异常 exceptionally() 回调给你一个从原始 Future 中生成的错误恢复的机会。
         * 你可以在这里记录这个异常并返回一个默认值。
         */
        Integer age = -1;
        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if (age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown! => exceptionally";
        });
        System.out.println("Maturity : " + maturityFuture.get());


        /**
         * 2. 使用 handle() 方法处理异常 API提供了一个更通用的方法 handle() 从异常恢复，无论一个异常是否发生它都会被调用。
         */
        Integer age2 = -1;
        CompletableFuture<String> maturityFuture2 = CompletableFuture.supplyAsync(() -> {
            if (age2 < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if (age2 > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).handle((res, ex) -> { // 如果异常发生，res 参数将是 null，否则，ex 将是 null
            if (ex != null) {
                System.out.println("Oops! We have an exception - " + ex.getMessage());
                return "Unknown! => handle";
            }
            return res;
        });
        System.out.println("Maturity : " + maturityFuture2.get());

    }

    /**
     * 假设你想下载一个网站的 100（可变）个不同的页面。你可以串行的做这个操作，但是这非常消耗时间。
     * 因此你想写一个函数，传入一个页面链接，返回一个 CompletableFuture，异步的下载页面内容。
     */
    private static CompletableFuture<String> downloadWebPage(String pageLink) {
        return CompletableFuture.supplyAsync(() -> "CompletableFuture => Code to download and return the web page's content with this: " + pageLink);
    }

    private static CompletableFuture<User> getUserDetail(int userId) {
        return CompletableFuture.supplyAsync(() -> new User(userId, "liyang", "S"));
    }

    private static CompletableFuture<String> getCreditRating(User user) {
        return CompletableFuture.supplyAsync(() -> "[" + user.getUserId() + ", " + user.getUserName() + ", " + user.getCreditRate() + "]");
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class User {
        int userId;
        String userName;
        String creditRate; // 信用评级 S - A - B - C - D
    }
}
