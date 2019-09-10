package com.xtb4.sortfromfiles;

import com.xtb4.sortfromfiles.data.Converter;
import com.xtb4.sortfromfiles.data.Input;
import com.xtb4.sortfromfiles.data.Output;
import com.xtb4.sortfromfiles.exceptions.ArgError;
import com.xtb4.sortfromfiles.logic.IOFabric;
import com.xtb4.sortfromfiles.logic.MergeSorter;
import com.xtb4.sortfromfiles.view.View;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
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

        if(order == null){
            View.showError(ArgError.WRONG_ORDER);
            return;
        }
        if(type == null){
            View.showError(ArgError.WRONG_TYPE);
            return;
        }
        if(outFile == null){
            View.showError(ArgError.WRONG_OUTPUT_FILE);
            return;
        }
        if(inFiles.size() < 1){
            View.showError(ArgError.WRONG_INPUT_FILE);
            return;
        }

        View.showStartMessage();

        Comparator comparator = order == Args.ORDER_ASC ? Comparator.naturalOrder() : Comparator.reverseOrder();
        Converter converter = type == Args.TYPE_INTEGER ? Converter.INTEGER_CONVERTER : Converter.STRING_CONVERTER;

        List<Input> inputs = new LinkedList<>();

        for(String file : inFiles){
            inputs.add(IOFabric.getInputs(file, converter));
        }

        Output output = IOFabric.getOutput(outFile, converter);

        MergeSorter sorter = new MergeSorter(comparator, inputs, output);
        sorter.sort();

        output.close();
        for (Input input : inputs) {
            input.close();
        }

        View.showResult(outFile);
    }
}
