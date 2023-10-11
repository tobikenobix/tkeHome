package primeharvester;


import javax.swing.text.html.HTMLDocument;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeHarvester implements Iterable<BigInteger>{

    public final BigInteger startValue;
    public final BigInteger endValue;

    public PrimeHarvester(BigInteger startValue, BigInteger endValue){
        //Value can not be null and startValue has to be greater 1 and endValue has to be greater startValue
        if(startValue == null || endValue == null){
            throw new IllegalArgumentException("invalid constructor params");
        }
        if(startValue.compareTo(new BigInteger("1"))<1 || startValue.compareTo(endValue)>0){
            throw new IllegalArgumentException("start value can not be smaller 1 or endValue");
        }

        this.startValue = startValue;
        this.endValue = endValue;
    }

    @Override
    public Iterator<BigInteger> iterator(){
        return new Iterator<BigInteger>() {
            BigInteger current = startValue.subtract(BigInteger.ONE);
            @Override
            public boolean hasNext() {
                return current.nextProbablePrime().compareTo(endValue) <= 0;
            }

            @Override
            public BigInteger next() {
                if (!hasNext()) throw new NoSuchElementException("End reached");
                current = current.nextProbablePrime();
                return current;
            }
        };
    }

    public long getPrimeCount(){
        long count=0;
        for (BigInteger bigInteger : this) {
            count++;
        }
        return count;
    }




}