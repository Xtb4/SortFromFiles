package test;

import com.xtb4.sortfromfiles.sorter.MergeSorter;
import com.xtb4.sortfromfiles.sorter.data.Input;
import com.xtb4.sortfromfiles.sorter.exceptions.SorterException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MergeSorterStringTest {

    private final MergeSorter<String> ascSorter = new MergeSorter<String>(Comparator.naturalOrder());
    private final MergeSorter<String> descSorter = new MergeSorter<String>(Comparator.reverseOrder());

    private final List<Input<String>> inputs = new LinkedList<>();
    private final OutputMock<String> output = new OutputMock<>();

    @Test
    void sortValidDataAscTest() throws SorterException {
        inputs.add(new InputMock<>(Arrays.asList("Hello,world!", "a", "apple", "brains", "test")));
        inputs.add(new InputMock<>(Arrays.asList("12", "brains", "h", "l", "p", "x")));
        inputs.add(new InputMock<>(Arrays.asList("Apple", "B", "Xtb4")));

        ascSorter.sort(inputs, output);

        assertEquals(output.data, Arrays.asList("12", "Apple", "B", "Hello,world!", "Xtb4", "a", "apple", "brains", "brains", "h", "l", "p", "test", "x"));
    }

    @Test
    void sortValidDataDescTest() throws SorterException {
        inputs.add(new InputMock<>(Arrays.asList("test", "brains", "apple", "a", "Hello,world!")));
        inputs.add(new InputMock<>(Arrays.asList("x", "p", "l", "h", "brains", "12")));
        inputs.add(new InputMock<>(Arrays.asList("Xtb4", "B", "Apple")));

        descSorter.sort(inputs, output);

        assertEquals(output.data, Arrays.asList("x", "test", "p", "l", "h", "brains", "brains", "apple", "a", "Xtb4", "Hello,world!", "B", "Apple", "12"));
    }

    @Test
    void sortInvalidDataTest() throws SorterException {
        inputs.add(new InputMock<>(Arrays.asList("a", "Hello,world!", "apple", "brains", "test")));
        inputs.add(new InputMock<>(Arrays.asList("12", "brains", "h", "l", "p", "x")));
        inputs.add(new InputMock<>(Arrays.asList("Xtb4", "B", "Apple")));

        ascSorter.sort(inputs, output);

        assertEquals(output.data, Arrays.asList("12", "Xtb4", "a", "apple", "brains", "brains", "h", "l", "p", "test", "x"));
    }


    @Test
    void sortNoInputsTest() {
        SorterException.ErrorType errorType = null;
        try {
            ascSorter.sort(inputs, output);
        } catch (SorterException ex) {
            errorType = ex.type;
        }
        assertEquals(SorterException.ErrorType.NO_INPUT_SOURCES, errorType);
    }

    @Test
    void sortNoOutputTest() {
        SorterException.ErrorType errorType = null;
        try {
            inputs.add(new InputMock<>(Arrays.asList("12", "brains", "h", "l", "p", "x")));
            ascSorter.sort(inputs, null);
        } catch (SorterException ex) {
            errorType = ex.type;
        }
        assertEquals(SorterException.ErrorType.NO_OUTPUT_SOURCE, errorType);
    }
}
