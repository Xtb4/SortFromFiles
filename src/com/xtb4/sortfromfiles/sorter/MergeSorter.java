package com.xtb4.sortfromfiles.sorter;

import com.xtb4.sortfromfiles.sorter.data.Input;
import com.xtb4.sortfromfiles.sorter.data.Output;
import com.xtb4.sortfromfiles.sorter.exceptions.InputTypeException;
import com.xtb4.sortfromfiles.sorter.exceptions.SorterException;

import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeSorter<T extends Comparable<? super T>> {

    private final Comparator<T> comparator;

    public MergeSorter(Comparator<T> comparator){
        this.comparator = comparator;
    }

    public void sort(List<Input<T>> inputs, Output<T> output) throws SorterException {
        if (inputs == null || inputs.isEmpty()) {
            throw new SorterException(SorterException.ErrorType.NO_INPUT_SOURCES);
        }
        if (output == null) {
            throw new SorterException(SorterException.ErrorType.NO_OUTPUT_SOURCE);
        }

        LinkedList<InputValue<T>> values = new LinkedList<>();

        for (Input<T> input : inputs) {
            InputValue<T> inputValue = new InputValue<>(input);
            inputValue.nextValue(comparator);
            addValue(values, inputValue);
        }

        try {
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
                    addValue(values, first);
                }
            }
        } catch (IOException ex) {
            throw new SorterException(SorterException.ErrorType.ERROR_WRITE_OUTPUT);
        }
    }

    private void addValue(LinkedList<InputValue<T>> values, InputValue<T> value){
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
                catch (InputTypeException e) {
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