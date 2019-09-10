package com.xtb4.sortfromfiles.data;

import java.io.IOException;

public interface Output<T> {

    void write(T output) throws IOException;
    void close();
}