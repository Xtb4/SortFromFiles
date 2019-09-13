package test;

import com.xtb4.sortfromfiles.sorter.data.Input;
import com.xtb4.sortfromfiles.sorter.exceptions.InputTypeException;

import java.util.Iterator;
import java.util.List;

public class InputMock<T> implements Input<T> {

    Iterator<T> iterator;

    public InputMock(Iterable<T> data) {
        iterator = data.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() throws InputTypeException {
        return iterator.next();
    }

    @Override
    public void close() {
    }
}
