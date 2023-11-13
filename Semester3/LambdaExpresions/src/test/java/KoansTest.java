import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Test;

import static org.junit.Assert.*;


public class KoansTest {
    @Test
    public void testMapArrayAdd(){
        int[] testArr = {1,2,3,4};
        //int[] resArr = {2,3,4,5};
        Koans.mapArray(testArr,x->x+1);
        assertArrayEquals(new int[] {2,3,4,5}, testArr);
    }

}
