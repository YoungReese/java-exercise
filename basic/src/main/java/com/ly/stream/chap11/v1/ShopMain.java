package com.ly.stream.chap11.v1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * liyang 2021-04-13
 *
 * Invocation returned after 442 msecs
 * Doing something else...
 * Price is 123.26
 * Price returned after 1574 msecs
 *
 * Process finished with exit code 0
 */
public class ShopMain {

    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("ss"); // my favorite product
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        // Do some more tasks, like querying other shops
        doSomethingElse();
        // while the price of the product is being calculated
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");

    }

    private static void doSomethingElse() {
        System.out.println("Doing something else...");
    }

}
