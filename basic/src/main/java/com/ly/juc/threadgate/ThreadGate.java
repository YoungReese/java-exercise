package com.ly.juc.threadgate;

public class ThreadGate {
    private boolean isOpen;
    private int generation;

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        ++generation;
        isOpen = true;
        notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        int arrivalGenerator = generation;
        while (!isOpen && arrivalGenerator == generation) {
            wait();
        }
    }
}
