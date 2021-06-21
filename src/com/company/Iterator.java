package com.company;

interface Iterator<V>{
    V next ();

    boolean hasNext();

    void remove();
}
