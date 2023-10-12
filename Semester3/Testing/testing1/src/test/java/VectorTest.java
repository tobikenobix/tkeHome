import FirstCode.Vector;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VectorTest {
    private Vector v1;
    @Before
    public void initialize(){
        v1 = new Vector(2,3);

    }
    @Test
    public void testDefaultConstructor(){
        Vector v = new Vector();
        assertEquals(0, v.getX(),0);
        assertEquals(0, v.getY(),0);
    }
    @Test
    public void testConstructor(){
        Vector v = new Vector(1,2);
        assertEquals(1, v.getX(),0);
        assertEquals(2, v.getY(),0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalCopyConstructor(){
        Vector x = new Vector(null);
    }

    @Test public void testCopyConstructor(){

        Vector v2 = new Vector(v1);
        assertEquals(v2,v1);
    }
    @Test public void testSetX(){
        v1.setX(7);
        assertEquals(7,v1.getX(),0);
    }
    @Test public void testSetY(){
        v1.setY(9);
        assertEquals(9, v1.getY(),0);
    }
    @Test public void testgetMagnitude(){
        assertEquals(Math.sqrt(13), v1.getMagnitude(), 0.00000);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalLengthNormalized(){
        v1.setY(0);
        v1.setX(0);
        v1.asNormalized();
    }
    @Test public void testAsNormalized(){
        v1.setY(0);
        v1.setX(1);
        Vector v2 = new Vector(0, 1);
        assertEquals(v2, v1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalAngle(){
        v1.fromPolar(9,2);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalMag(){
        v1.fromPolar(1, -1);
    }

    @Test public void testFromPolar(){
        double x = 1 *Math.cos(1);
        double y = 1 * Math.sin(1);
        Vector v2 = new Vector(x,y);
        assertEquals(v2, v1.fromPolar(1,1));
    }


}
