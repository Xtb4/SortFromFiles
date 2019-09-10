package com.xtb4.sortfromfiles.data.IO;

import com.xtb4.sortfromfiles.data.Converter;
import com.xtb4.sortfromfiles.data.Output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OutputFile<T> implements Output<T> {

    private final PrintWriter writer;
    private final Converter<T> converter;

    public OutputFile(String file, Converter<T> converter) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        this.writer = new PrintWriter(fileWriter);
        this.converter = converter;
    }

    @Override
    public void write(T output) {
        String outputString = converter.convert(output);
        writer.println(outputString);
    }

    @Override
    public void close() {
        writer.close();
    }
}