package com.xtb4.sortfromfiles;

import com.xtb4.sortfromfiles.sorter.data.Converter;
import com.xtb4.sortfromfiles.sorter.data.Input;
import com.xtb4.sortfromfiles.sorter.data.Output;
import com.xtb4.sortfromfiles.sorter.IOFactory;
import com.xtb4.sortfromfiles.sorter.MergeSorter;
import com.xtb4.sortfromfiles.sorter.exceptions.SorterException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Args order = Args.DEFAULT_ORDER;
        Args type = null;
        String outFile = null;
        List<String> inFiles = new LinkedList<>();

        for (String arg: args) {
            if (Args.ORDER_ASC.token.equals(arg)) {
                order = Args.ORDER_ASC;
            }
            else if (Args.ORDER_DESC.token.equals(arg)) {
                order = Args.ORDER_DESC;
            }
            else if (Args.TYPE_INTEGER.token.equals(arg)) {
                type = Args.TYPE_INTEGER;
            }
            else if (Args.TYPE_STRING.token.equals(arg)) {
                type = Args.TYPE_STRING;
            }
            else if (outFile == null) {
                outFile = arg;
            }
            else {
                inFiles.add(arg);
            }
        }

        if(type == null) {
            System.out.println(Messages.WRONG_TYPE);
            return;
        }
        if(outFile == null) {
            System.out.println(Messages.WRONG_OUTPUT_FILE);
            return;
        }
        if(inFiles.isEmpty()) {
            System.out.println(Messages.WRONG_INPUT_FILE);
            return;
        }

        Comparator comparator = order == Args.ORDER_ASC ? Comparator.naturalOrder() : Comparator.reverseOrder();
        Converter converter = type == Args.TYPE_INTEGER ? Converter.INTEGER_CONVERTER : Converter.STRING_CONVERTER;

        List<Input> inputs = new LinkedList<>();

        for(String file : inFiles) {
            try {
                inputs.add(IOFactory.getInputs(file, converter));
            } catch (FileNotFoundException e) {
                System.out.println(String.format(Messages.INPUT_FILE_NOT_FOUND, file));
            }
        }

        Output output = null;
        MergeSorter sorter = new MergeSorter(comparator);

        try {
            output = IOFactory.getOutput(outFile, converter);
            sorter.sort(inputs, output);
        } catch (SorterException e) {
            handleException(e.type);
        } catch (IOException e) {
            System.out.println(Messages.NO_OUTPUT_SOURCE);
        }
        finally {
            for (Input input : inputs) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        }
    }

    private static void handleException(SorterException.ErrorType errorType) {
        switch (errorType) {
            case NO_INPUT_SOURCES:
                System.out.println(Messages.NO_INPUT_SOURCES);
                break;
            case NO_OUTPUT_SOURCE:
                System.out.println(Messages.NO_OUTPUT_SOURCE);
                break;
            case ERROR_WRITE_OUTPUT:
                System.out.println(Messages.ERROR_WRITE_OUTPUT);
        }
    }
}
