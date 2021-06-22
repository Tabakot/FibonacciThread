package com.company;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Fibonacci implements Iterator<BigInteger> {
    private final AtomicReference<FibonacciNumber> cacheValue;


    public Fibonacci()
    {
        cacheValue = new AtomicReference<>(new FibonacciNumber(BigInteger.ONE, BigInteger.ONE));
    }

    @Override
    public BigInteger next() {
        BigInteger next;
        BigInteger cur;
        FibonacciNumber value;
        do {
            value = cacheValue.get();
            cur = value.getNext();
            next = value.getPrev().add(value.getNext());
        } while(!cacheValue.compareAndSet(value, new FibonacciNumber(cur, next)));
        return next;
    }

    @Override
    public boolean hasNext() {
        return cacheValue.get().getNext().bitLength() < 64;
    }

    @Override
    public void remove() {

    }
}
