package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        List<FibonacciThread> fibonacciThreads = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            FibonacciThread fibThr = new FibonacciThread(i + 1, fib);
            fibonacciThreads.add(fibThr);
        }
        int summa;
        do {
            summa = 0;
            for (FibonacciThread fibonacciThread : fibonacciThreads) {
                if (!fibonacciThread.thread.isAlive()) {
                    fibonacciThread.printSequence();
                    summa++;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
        } while (summa < fibonacciThreads.size());
        System.exit(0);
    }
}
