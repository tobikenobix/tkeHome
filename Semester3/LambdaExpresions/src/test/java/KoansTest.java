import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.Assert.*;


public class KoansTest {
    public double random(){
        Random rando = new Random(1234);
        return rando.nextDouble();
    }
    @Test
    public void testMapArrayAdd(){
        int[] testArr = {1,2,3,4};
        //int[] resArr = {2,3,4,5};
        Koans.mapArray(testArr,x->x+1);
        assertArrayEquals(new int[] {2,3,4,5}, testArr);
    }
    @Test
    public void testMapArrayMult(){
        int[] testArr={1,2,3,4};
        int[] resArr={1,4,9,16};
        Koans.mapArray(testArr, x->x*x);
        assertArrayEquals(testArr,resArr);
    }

    @Test
    public void testFillArrayPI(){
        double[] expected = {Math.PI, Math.PI, Math.PI};
        assertArrayEquals(expected, Koans.fillArray(3,()->Math.PI), 1.0E-09);
    }

    @Test
    public void testFillArrayRandom(){
        double[] expected = {random(), random(), random()};
        assertArrayEquals(expected, Koans.fillArray(3, this::random), 1.E-09);

    }

    @Test
    public void testIterateFunctionAddONe(){
        int[] expected = {0,1,2,3,4};
        assertArrayEquals(expected, Koans.iterateFunction(5,0,x->x+1));
    }

    @Test
    public void testMinInt(){
        Integer expected = -1;
        Integer[] testingElemens = {4,1,-1,2,0};
        assertEquals(expected, Koans.min(testingElemens, (x,y) -> x-y));
    }

    @Test
    public void testMinString(){
        String expected ="hi";
        String[] testingStrings = {"hi","Servus", "Ciao", "hii"};
        assertEquals(expected, Koans.min(testingStrings, (x,y)->x.length()-y.length()));
    }

    @Test
    public void testCreateMultiplier(){
        double expected = 20.0;
        Function<Double,Double>f=Koans.createMultiplier(5);
        assertEquals(expected,f.apply(4.0),1.E-09);
    }
    @Test
    public void testForEachArray(){
        String expected = "HalloduWelt";
        String[] testArr = {"Hallo","du","Welt"};
        StringBuilder sb = new StringBuilder();
        Koans.forEachArray(testArr, sb::append);
        assertEquals(expected, sb.toString());
    }

    @Test
    public void testDuplicateChecker(){
        Predicate<String> ps = Koans.duplicateChecker();
        assertFalse(ps.test("hallo"));
    }

    @Test
    public void testDuplicateCheckerTwoTimes(){
        Predicate<Integer> pi = Koans.duplicateChecker();
        pi.test(2);
        assertTrue(pi.test(2));
    }

}
