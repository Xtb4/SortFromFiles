package com.xtb4.sortfromfiles.logic;

import com.xtb4.sortfromfiles.data.Input;
import com.xtb4.sortfromfiles.data.Output;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeSorter {

    private final Comparator comparator;
    private List<Input> inputs;
    private final Output output;

    public MergeSorter(Comparator comparator, List<Input> files, Output output){
        this.comparator = comparator;
        this.inputs = files;
        this.output = output;
    }

    public <T extends Comparable<? super T>> void sort() throws IOException {

        LinkedList<InputValue<T>> values = new LinkedList<>();

        for (Input<T> input : inputs) {
            InputValue<T> inputValue = new InputValue<>(input);
            inputValue.nextValue(comparator);
            addValue(values, inputValue, comparator);
        }

        while (!values.isEmpty()) {

            InputValue<T> first = values.getFirst();

            output.write(first.lastVal);
            first.nextValue(comparator);

            if (first.lastVal == null) {
                values.removeFirst();
            }

            if (values.size() > 1 && comparator.compare(values.get(0).lastVal, values.get(1).lastVal) > 0) {
                first = values.getFirst();
                values.removeFirst();
                addValue(values, first, comparator);
            }
        }
    }

    private static <T extends Comparable<? super T>> void addValue(LinkedList<InputValue<T>> values,
                                                                   InputValue<T> value,
                                                                   Comparator<T> comparator){
        if (value.lastVal == null) {
            return;
        }

        for(int position = 0; position < values.size(); position++) {
            if (comparator.compare(values.get(position).lastVal, value.lastVal) >= 0) {
                values.add(position, value);
                return;
            }
        }
        values.addLast(value);
    }

    private static class InputValue<T> {

        Input<T> input;

        T lastVal;

        InputValue(Input<T> input) {
            this.input = input;
        }

        void nextValue(Comparator<T> comparator) {
            while (input.hasNext()) {
                try {
                    T val = input.next();

                    if (lastVal == null || comparator.compare(val, lastVal) >= 0) {
                        lastVal = val;
                        return;
                    }
                }
                catch (IllegalArgumentException ex) {
                    //ignore
                }
            }
            lastVal = null;
        }

        @Override
        public String toString() {
            return String.valueOf(lastVal);
        }
    }
}