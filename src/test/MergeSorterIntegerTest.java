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

class MergeSorterIntegerTest {

    private final MergeSorter<Integer> ascSorter = new MergeSorter<Integer>(Comparator.naturalOrder());
    private final MergeSorter<Integer> descSorter = new MergeSorter<Integer>(Comparator.reverseOrder());

    private final List<Input<Integer>> inputs = new LinkedList<>();
    private final OutputMock<Integer> output = new OutputMock<>();

    @Test
    void sortValidDataAscTest() throws SorterException {
        inputs.add(new InputMock<>(Arrays.asList(1, 3, 4, 4, 6, 8)));
        inputs.add(new InputMock<>(Arrays.asList(2, 5, 5, 5, 6, 12)));
        inputs.add(new InputMock<>(Arrays.asList(0, 5, 7)));

        ascSorter.sort(inputs, output);

        assertEquals(output.data, Arrays.asList(0, 1, 2, 3, 4, 4, 5, 5, 5, 5, 6, 6, 7, 8, 12));
    }

    @Test
    void sortValidDataDescTest() throws SorterException {
        inputs.add(new InputMock<>(Arrays.asList(8, 6, 4, 4, 3, 1)));
        inputs.add(new InputMock<>(Arrays.asList(12, 6, 5, 5, 5, 2)));
        inputs.add(new InputMock<>(Arrays.asList(7, 5, 0)));

        descSorter.sort(inputs, output);

        assertEquals(output.data, Arrays.asList(12, 8, 7, 6, 6, 5, 5, 5, 5, 4, 4, 3, 2, 1, 0));
    }

    @Test
    void sortInvalidDataTest() throws SorterException {
        inputs.add(new InputMock<>(Arrays.asList(1, 3, 4, 2, 1, 8)));
        inputs.add(new InputMock<>(Arrays.asList(2, 5, 5, 5, 0, 4)));
        inputs.add(new InputMock<>(Arrays.asList(7, 5, 0)));

        ascSorter.sort(inputs, output);

        assertEquals(output.data, Arrays.asList(1, 2, 3, 4, 5, 5, 5, 7, 8));
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
            inputs.add(new InputMock<>(Arrays.asList(1, 3, 4, 2, 1, 8)));
            ascSorter.sort(inputs, null);
        } catch (SorterException ex) {
            errorType = ex.type;
        }
        assertEquals(SorterException.ErrorType.NO_OUTPUT_SOURCE, errorType);
    }
}