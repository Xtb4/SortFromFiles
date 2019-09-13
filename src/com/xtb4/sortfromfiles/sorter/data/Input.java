package com.xtb4.sortfromfiles.sorter.data;

import com.xtb4.sortfromfiles.sorter.exceptions.InputTypeException;

public interface Input<T> {

    boolean hasNext();
    T next() throws InputTypeException;
    void close();
}