package com.ly.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * liyang 2020-12-15
 * 模拟哲学家就餐，死锁
 *
 * 解决方法：
 * 服务员检查（避免策略）
 * 改变一个哲学家拿筷子的顺序（避免策略）
 * 餐票（避免策略）
 * 领导调节（检测与恢复策略）
 *
 */
public class DiningPhilosophers {
    public static void main(String[] args) {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] chopsticks = new Object[philosophers.length];
        for (int i = 0; i < chopsticks.length; i++) {
            chopsticks[i] = new Object();
        }
        for (int i = 0; i < philosophers.length; i++) {
            Object leftChopstick = chopsticks[i];
            Object rightChopstick = chopsticks[(i + 1) % chopsticks.length];
//            philosophers[i] = new Philosopher(leftChopstick, rightChopstick); // 会发生死锁

            // 破坏互斥条件：改变一个哲学家拿筷子的顺序（避免策略）
            if (i == philosophers.length - 1) {
                philosophers[i] = new Philosopher(rightChopstick, leftChopstick);
            } else {
                philosophers[i] = new Philosopher(leftChopstick, rightChopstick);
            }
            new Thread(philosophers[i], "哲学家" + (i + 1) + "号：").start();
        }
    }


    static class Philosopher implements Runnable {
        private final Object leftChopstick;
        private final Object rightChopstick;

        public Philosopher(Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("Thinking");
                    synchronized (leftChopstick) {
                        doAction("Pick up left chopstick");
                        synchronized (rightChopstick) {
                            doAction("Pick up right chopstick");
                            doAction("Eating");
                            doAction("Put down right chopstick");
                        }
                        doAction("Put down left chopstick");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + action);
            TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 10));
        }
    }

}
