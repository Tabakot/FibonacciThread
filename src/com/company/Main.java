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
            for (int i = 0; i < fibonacciThreads.size(); i++) {
                if (!fibonacciThreads.get(i).thread.isAlive()) {
                    fibonacciThreads.get(i).printSequence();
                    summa++;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        } while (summa < fibonacciThreads.size());
        System.out.println(Fibonacci.arrayList);
        System.exit(0);
    }
}
