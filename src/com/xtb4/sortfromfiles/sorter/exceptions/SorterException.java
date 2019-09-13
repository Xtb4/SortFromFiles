package com.xtb4.sortfromfiles.sorter.exceptions;

public class SorterException extends Exception {

    public final ErrorType type;

    public SorterException(ErrorType type) {
        this.type = type;
    }

    public enum ErrorType {
        NO_INPUT_SOURCES,
        NO_OUTPUT_SOURCE,
        ERROR_WRITE_OUTPUT
    }
}
