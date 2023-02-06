package ngordnet.ngrams;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TestTimeSeries {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994, 1995));

        assertEquals(expectedYears, totalPopulation.years());

        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(0.0, 100.0, 600.0, 500.0));

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertEquals(expectedTotal.get(i), totalPopulation.data().get(i), 1E-10);
        }
    }

    @Test
    public void testDivided() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1991, 400.0);
        dogPopulation.put(1992, 500.0);
        dogPopulation.put(1993, 800.0);
        dogPopulation.put(1994, 800.0);
        dogPopulation.put(1995, 800.0);

        TimeSeries dividedPopulation = catPopulation.dividedBy(dogPopulation);
        // expected: 1991: 0,
        //           1992: 0.2
        //           1994: 0.25

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994));

        assertEquals(expectedYears, dividedPopulation.years());

        List<Double> expectedDivided = new ArrayList<>
                (Arrays.asList(0.0, 0.2, 0.25));

        for (int i = 0; i < expectedDivided.size(); i += 1) {
            assertEquals(expectedDivided.get(i), dividedPopulation.data().get(i), 1E-10);
        }

    }

    @Test
    public void testCopy() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1995, 0.0);
        catPopulation.put(1994, 100.0);
        catPopulation.put(1992, 200.0);
        catPopulation.put(1991, 250.0);

        TimeSeries catPopulation2 = new TimeSeries(catPopulation, 1991, 1994);

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994));
        List<Double> expectedValue = new ArrayList<>
                (Arrays.asList(250.0, 200.0, 100.0));
        assertEquals(expectedYears, catPopulation2.years());
       assertEquals(expectedValue, catPopulation2.data());
    }
} 