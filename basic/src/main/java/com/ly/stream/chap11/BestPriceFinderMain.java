package com.ly.stream.chap11;

import java.util.List;
import java.util.function.Supplier;

/**
 * liyang 2021-04-13
 *
 * [BestPrice price is 114.17, LetsSaveBig price is 158.69, MyFavoriteShop price is 201.71, BuyItAll price is 173.40, ShopEasy price is 165.06]
 * sequential done in 5289 msecs
 * [BestPrice price is 185.36, LetsSaveBig price is 156.88, MyFavoriteShop price is 180.86, BuyItAll price is 187.79, ShopEasy price is 188.84]
 * parallel done in 2019 msecs
 * [BestPrice price is 160.27, LetsSaveBig price is 157.85, MyFavoriteShop price is 163.82, BuyItAll price is 144.59, ShopEasy price is 153.40]
 * composed CompletableFuture done in 1015 msecs
 *
 * Process finished with exit code 0
 */
public class BestPriceFinderMain {
    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    public static void main(String[] args) {
        execute("sequential", () -> bestPriceFinder.findPricesSequential("iphone 12"));
        execute("parallel", () -> bestPriceFinder.findPricesParallel("iphone 12"));
        execute("composed CompletableFuture", () -> bestPriceFinder.findPricesFuture("iphone 12"));
    }

    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
