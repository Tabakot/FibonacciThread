package com.company;

import java.math.BigInteger;

class FibonacciThread extends Thread {
    Thread thread;
    int id;
    Fibonacci fib;
    BigInteger fibNumber;
    boolean printed = false;

    FibonacciThread(final int id, Fibonacci fib)
    {
        this.id = id;
        this.fib = fib;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            if (fib.hasNext()) {
                fibNumber = fib.next();
            }
            Thread.sleep((long) (10 * Math.random()));
        } catch (InterruptedException e) {
            System.out.printf("Поток %2d прерван%n", id);
        }
        printSequence();
    }
    public void printSequence()
    {
        if (printed)
            return;
        System.out.printf("Поток %2d : [%8d]%n", id, fibNumber);
        printed = true;
    }
}