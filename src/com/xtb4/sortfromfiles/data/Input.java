package com.xtb4.sortfromfiles.data;

public interface Input<T> {

    boolean hasNext();
    T next();
    void close();
}