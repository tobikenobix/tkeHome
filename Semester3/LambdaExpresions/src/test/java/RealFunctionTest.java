import org.junit.Test;
import static org.junit.Assert.*;

public class RealFunctionTest {
    @Test
    public void testConstant(){
        RealFunction c = RealFunction.constant(2);
        assertEquals(2.0,c.apply(2),0.0000000);
    }
    @Test
    public void testlinear(){
        RealFunction l = RealFunction.linear(1,2);
        assertEquals(4.0, l.apply(2), 0.0000000);
    }
    @Test
    public void testSine(){
        RealFunction s = RealFunction.sine(1,2);
        assertEquals(1*Math.sin(2*2), s.apply(2), 0.000000000);
    }

    @Test
    public void testExp(){
        RealFunction e = RealFunction.exp();
        assertEquals(Math.exp(2), e.apply(2), 0.0000000);
    }

    @Test
    public void testAddTo(){
        assertEquals(1+Math.sin(1), RealFunction.linear(1,0).addTo(RealFunction.sine(1,1)).apply(1),0.0000000);
    }

    @Test
    public void testCompose(){
        assertEquals(Math.exp(2*2+1), RealFunction.exp().composeWith(RealFunction.linear(2,1)).apply(2), 0.000000);
    }
    @Test
    public void testMultiplyWithEmpty(){
        assertThrows(IllegalArgumentException.class, ()->RealFunction.exp().multiplyWith());

    }

    @Test
    public void testMulitplyWith(){
        assertEquals(2*Math.exp(2)*Math.sin(2), RealFunction.linear(1,0).multiplyWith(RealFunction.exp(),RealFunction.sine(1,1)).apply(2), 0.0000);
    }

    @Test
    public void realFunctionApproxDiff(){

        RealFunction f = RealFunction.sine(2.1,-1.5);
        RealFunction fd = f.approxDiff(1e-5);

        assertEquals(0.19639782101202483368, fd.apply(-3.1), 1e-3);
        assertEquals(-3.1496456316444814162, fd.apply(0.01), 1e-3);
    }
    @Test
    public void testMax(){
        assertEquals(Math.exp(2), RealFunction.exp().max(RealFunction.constant(2)).apply(2), 1e-5);
    }

}
