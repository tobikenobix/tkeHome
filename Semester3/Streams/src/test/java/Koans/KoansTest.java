package Koans;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

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

}
