import FirstTest.Fraction;
import org.junit.Test;

import static org.junit.Assert.*;

public class FractionTest {
    //test constructor block
    @Test
    public void testConstructorValid(){
        Fraction f = new Fraction(1,2);
        assertEquals(1, f.getNom());
        assertEquals(2, f.getDenom());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalid(){
        Fraction f = new Fraction(1,0);
    }
    @Test
    public void testCopyConstructor(){
        Fraction f = new Fraction(2,3);
        Fraction f1 = new Fraction(f);
        assertEquals(2, f1.getNom());
        assertEquals(3, f1.getDenom());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCopyConstructorInvalid(){
        Fraction f = new Fraction(null);
    }
    @Test
    public void testSetterValid(){
        Fraction f = new Fraction(1,2);
        f.setNom(3);
        f.setDenom(1);
        assertEquals("nom was not changed", 3, f.getNom());
        assertEquals("doneom was not changed", 1, f.getDenom());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetterInvalid(){
        Fraction f = new Fraction(1,2);
        f.setDenom(0);
    }
    @Test
    public void testNormalizeSign(){
        Fraction f = new Fraction(-2,-3);
        f.normalizeSign();
        assertEquals("nom has to be negative",-2, f.getNom());
        assertEquals("denom has to be positive", 3, f.getDenom());
        f.setNom(1);
        f.setDenom(2);
        f.normalizeSign();
        assertEquals("Nom has to be positve", 1, f.getNom());
        assertEquals("Denom has to be negative", 2, f.getDenom());
        f.setDenom(-2);
        f.normalizeSign();
        assertEquals("Nom has to be negative", -1, f.getNom());
        assertEquals("Denom has to switch to positve", 2, f.getDenom());
        f.setNom(0);
        f.normalizeSign();
        assertEquals("Denom has to be one, if nom is 0", 1, f.getDenom());
        f.setNom(-5);
        f.setDenom(3);
        assertEquals("Only nom should be negative", -5, f.getNom());
        assertEquals("Only nom should be negative", 3, f.getDenom());
    }

    @Test
    public void testAsReducedPositive(){
        Fraction f = new Fraction(3,6);
        Fraction f1 = f.asReduced();
        assertEquals(1,f1.getNom());
        assertEquals(2, f1.getDenom());
    }
    @Test
    public void testAsReducedNegative(){
        Fraction f = new Fraction(-4,-8);
        Fraction f1 =f.asReduced();
        assertEquals(-1, f1.getNom());
        assertEquals(-2, f1.getDenom());
    }
    @Test
    public void testAsReducedMixed(){
        Fraction f = new Fraction(-2,8);
        Fraction f1 = f.asReduced();
        //assertEquals(-1,f1.getNom());
        assertEquals(4, f1.getDenom());
    }
    @Test
    public void testAsReducedSameValues(){
        Fraction f = new Fraction(1,1);
        Fraction f1 = f.asReduced();
        assertEquals(1, f1.getNom());
        assertEquals(1, f1.getDenom());
    }

    @Test
    public void testValue(){
        Fraction f = new Fraction(2,6);
        assertEquals((double)2/6, f.value(), 0.0000);
    }

    @Test
    public void testEqualsSame(){
        Fraction f = new Fraction(1,2);
        Fraction f1 = new Fraction(1,2);
        assertEquals(f, f1);
    }
    @Test
    public void testEqualsDifferent(){
        Fraction f = new Fraction(1,2);
        Fraction f1 = new Fraction(2,3);
        assertNotEquals(f, f1);
    }

    @Test
    public void testValueEqualsSame(){
        Fraction f = new Fraction(2,4);
        Fraction f1 = new Fraction(3,6);
        assertTrue(f.valueEquals(f1));
    }
    @Test
    public void testValueEqualsDifferent(){
        Fraction f = new Fraction(3,4);
        Fraction f1 = new Fraction(5,6);
        assertFalse(f.valueEquals(f1));
    }
}
