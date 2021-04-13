package com.ly.stream.chap11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * liyang 2021-04-13
 */
public class BestPriceFinder {
    private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"));

    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
        Thread t = new Thread(r);
        t.setDaemon(true); // 使用守护线程 -- 这种方式不会阻止程序的关停
        return t;
    });

    /**
     * 同步查询
     */
    public List<String> findPricesSequential(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    public static void main(String[] args) {
        BestPriceFinder finder = new BestPriceFinder();
        finder.testFindPricesSequential();
    }

    public void testFindPricesSequential() {
        long start = System.nanoTime();
        System.out.println(findPricesSequential("iphone 12"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }


    /**
     * CompletableFuture 异步查询
     */
    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)), executor))
                .collect(toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)  // 等待所有异步操作结束
                .collect(Collectors.toList());
    }


    /**
     * stream 流异步查询
     */
    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

}
