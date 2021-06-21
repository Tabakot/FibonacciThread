package com.company;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.StampedLock;

public class Fibonacci implements Iterator<BigInteger> {
    private final AtomicReference<BigInteger> cacheValue;
    private final AtomicReference<BigInteger> cachePrev;
    private final StampedLock lock = new StampedLock();




    public Fibonacci()
    {
        cacheValue = new AtomicReference<>(BigInteger.ONE);
        cachePrev = new AtomicReference<>(BigInteger.ONE);
    }

    @Override
    public BigInteger next() {
        BigInteger next;
        BigInteger prev;
        BigInteger value;
        long stamp = lock.writeLock();
        try {
            prev = cachePrev.get();
            value = cacheValue.get();
            next = prev.add(value);
            cacheValue.compareAndSet(value, next);
            cachePrev.compareAndSet(prev, value);
        } finally {
            lock.unlock(stamp);
        }
        return next;
    }

    @Override
    public boolean hasNext() {
        return cacheValue.get().bitLength() < 64;
    }

    @Override
    public void remove() {

    }
}
