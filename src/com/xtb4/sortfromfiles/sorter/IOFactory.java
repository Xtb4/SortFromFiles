package com.xtb4.sortfromfiles.sorter;

import com.xtb4.sortfromfiles.sorter.data.Converter;
import com.xtb4.sortfromfiles.sorter.data.IO.InputFile;
import com.xtb4.sortfromfiles.sorter.data.IO.OutputFile;
import com.xtb4.sortfromfiles.sorter.data.Input;
import com.xtb4.sortfromfiles.sorter.data.Output;

import java.io.FileNotFoundException;
import java.io.IOException;

public class IOFactory {

    public static Input getInputs(String file, Converter converter) throws FileNotFoundException {
        return new InputFile(file, converter);
    }

    public static Output getOutput(String file, Converter converter) throws IOException {
        return new OutputFile(file, converter);
    }
}