package test;

import com.xtb4.sortfromfiles.sorter.data.Output;

import java.util.LinkedList;
import java.util.List;

public class OutputMock<T> implements Output<T> {

    List<T> data = new LinkedList<>();

    @Override
    public void write(T output) {
        data.add(output);
    }

    @Override
    public void close() {
    }
}
