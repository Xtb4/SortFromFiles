package com.xtb4.sortfromfiles.data;

public interface Converter<T> {

    T convert(String input);
    String convert (T input);

    Converter<String> STRING_CONVERTER = new Converter<>(){
        @Override
        public String convert(String input) {
            return input;
        }
    };

    Converter<Integer> INTEGER_CONVERTER = new Converter<>() {

        @Override
        public Integer convert(String input) {
            try{
                return Integer.parseInt(input);
            }
            catch (Exception e){
                throw new IllegalArgumentException();
            }
        }

        @Override
        public String convert(Integer input) {
            return input.toString();
        }
    };
}