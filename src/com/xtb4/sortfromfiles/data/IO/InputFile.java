package com.xtb4.sortfromfiles.data.IO;

import com.xtb4.sortfromfiles.data.Converter;
import com.xtb4.sortfromfiles.data.Input;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class InputFile<T> implements Input<T> {

    private final Scanner scanner;
    private final Converter<T> converter;

    public InputFile(String file, Converter<T> converter) throws FileNotFoundException {
        FileReader reader = new FileReader(file);
        scanner = new Scanner(reader);
        this.converter = converter;
    }

    @Override
    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    @Override
    public T next() {
        String input = scanner.nextLine();
        return converter.convert(input);
    }

    @Override
    public void close() {
        scanner.close();
    }
}