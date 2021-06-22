package com.company;

import java.math.BigInteger;

public class FibonacciNumber {
    private final BigInteger prev;
    private final BigInteger next;

    public FibonacciNumber(BigInteger prev, BigInteger next) {
        this.prev = prev;
        this.next = next;
    }

    public BigInteger getPrev() {
        return prev;
    }

    public BigInteger getNext() {
        return next;
    }
}
