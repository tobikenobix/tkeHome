package Koans;

import org.junit.Test;

import java.util.*;
import java.util.stream.LongStream;

import static org.junit.Assert.*;
import static Koans.Koans.*;
public class KoansTest {
    @Test
    public void testDistinctEntries(){
        ArrayList<Integer> testArrInts = new ArrayList<>();
        Collections.addAll(testArrInts, 1,2,3,4,2,3,3);
        LinkedList<String> testStrings = new LinkedList<>();
        Collections.addAll(testStrings, "a","b","c","d","a");
        assertEquals(4, distinctEntries(testArrInts));
        assertEquals(4, distinctEntries(testStrings));
    }

    @Test
    public void testCountEvenNumbers(){
        int[] testInts = {1,2,3,4,5};
        assertEquals(2, countEvenNumbers(testInts));
    }

    @Test
    public void testIntsFromStrings(){
        String[] st = {"1","2","3","4"};
        int[] expected = {1,2,3,4};
        assertArrayEquals(expected, intsFromString(st));
    }

    @Test
    public void koansRandomSum(){
        double[] result = randomSum(10);

        assertNotNull(result);
        assertEquals(10, result.length);

        assertEquals(0.0, result[0], 1e-15);

        for (int i = 1; i < result.length; i++){
            assertTrue( result[i]-result[i-1] >= 0 );
            assertTrue( result[i]-result[i-1] < 1 );
        }

    }

    @Test
    public void testDotProduct(){
        double[] v1 = {1,2,3};
        double[] v2 = {4,5,6};
        double expected = 32;
        assertEquals(expected,dotProduct(v1,v2), 1e-15);
    }

    @Test
    public void testStringsForLength(){
        ArrayList<String> testStrings = new ArrayList<>();
        Collections.addAll(testStrings, "a","b","cc","dd","eee");
        ArrayList<String> expected = new ArrayList<>();
        Collections.addAll(expected,"a","b");
        Map<Integer, List<String>> s = stringsForLength(testStrings);
        assertEquals(expected,s.get(1));
    }

    @Test
    public void testCollatzSeries(){
        LongStream test = collatzSeries(11);
        long[] testArr = test.limit(3).toArray();
        long[] expected = {11,34,17};
        assertArrayEquals(expected,testArr);
    }

    @Test
    public void testCollatzTruncated(){
        long[] expected = {10,5,16,8,4,2,1};
        assertArrayEquals(expected, collatzTruncated(10).toArray());
    }

    @Test
    public void testCollatzOrbit(){
        assertTrue(collatzOrbit(10));
    }

    @Test
    public void testCollatzTrueForLimit(){
        assertTrue(collatzTrueForLimit(100));
    }


}
