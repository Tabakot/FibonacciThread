package com.company;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Fibonacci implements Iterator<BigInteger> {
    private final AtomicReference<BigInteger> cacheValue;
    private final AtomicReference<BigInteger> cachePrev;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();


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

        lock.writeLock().lock();
        try {
            prev = cachePrev.get();
            value = cacheValue.get();
            next = prev.add(value);
            cacheValue.compareAndSet(value, next);
            cachePrev.compareAndSet(prev, value);
        } finally {
            lock.writeLock().unlock();
        }
        return next;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public void remove() {

    }
}
