package com.ly.stream.chap11.v2;

import java.util.List;
import java.util.function.Supplier;

public class BestPriceFinderMain {

    private static BestPriceFinder bestPriceFinder = new BestPriceFinder();

    public static void main(String[] args) {
        execute("sequential", () -> bestPriceFinder.findPricesSequential("iPhone 12"));
        execute("parallel", () -> bestPriceFinder.findPricesParallel("iPhone 12"));
        execute("composed CompletableFuture", () -> bestPriceFinder.findPricesFuture("iPhone 12"));
        System.out.println(" ");
        bestPriceFinder.printPricesStream("iPhone 12");
    }

    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }

}
