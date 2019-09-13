package com.xtb4.sortfromfiles.sorter.data;

import com.xtb4.sortfromfiles.sorter.exceptions.InputTypeException;

public interface Converter<T> {

    T convert(String input) throws InputTypeException;
    String convert (T input);

    Converter<String> STRING_CONVERTER = new Converter<>(){
        @Override
        public String convert(String input) {
            return input;
        }
    };

    Converter<Integer> INTEGER_CONVERTER = new Converter<>() {

        @Override
        public Integer convert(String input) throws InputTypeException {
            try{
                return Integer.parseInt(input);
            }
            catch (Exception e){
                throw new InputTypeException();
            }
        }

        @Override
        public String convert(Integer input) {
            return input.toString();
        }
    };
}