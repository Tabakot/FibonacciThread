package com.company;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Fibonacci implements Iterator<BigInteger> {
    private AtomicReference<BigInteger> cacheValue;
    private AtomicReference<BigInteger> cachePrev;
    static List<BigInteger> arrayList;
    private BigInteger next;
    private BigInteger prev;
    private BigInteger value;

    public Fibonacci()
    {
        prev = BigInteger.ONE;
        cacheValue = new AtomicReference<>(BigInteger.ONE);
        cachePrev = new AtomicReference<>(BigInteger.ONE);
        arrayList = new ArrayList<>();
        arrayList.add(BigInteger.ONE);
    }

    @Override
    public BigInteger next() {
        do {
            value = cacheValue.get();
            next = prev.add(value);
        } while (hasNext());
        prev = value;
        arrayList.add(next);
        return next;
    }

    @Override
    public boolean hasNext() {
        return !cacheValue.compareAndSet(value, next);
    }

    @Override
    public void remove() {

    }
}
