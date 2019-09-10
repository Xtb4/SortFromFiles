package com.xtb4.sortfromfiles.logic;

import com.xtb4.sortfromfiles.data.Converter;
import com.xtb4.sortfromfiles.data.IO.InputFile;
import com.xtb4.sortfromfiles.data.IO.OutputFile;
import com.xtb4.sortfromfiles.data.Input;
import com.xtb4.sortfromfiles.data.Output;

import java.io.FileNotFoundException;
import java.io.IOException;

public class IOFabric {

    public static Input getInputs(String file, Converter converter) throws FileNotFoundException {
        return new InputFile(file, converter);
    }

    public static Output getOutput(String file, Converter converter) throws IOException {
        return new OutputFile(file, converter);
    }
}