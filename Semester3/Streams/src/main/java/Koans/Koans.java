package Koans;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Koans {
    public static<T> long distinctEntries(Collection<T> entries){
        return entries.stream().distinct().count();
    }

    public static long countEvenNumbers(int[] numbers){
        return Arrays.stream(numbers).asLongStream().filter(x -> x%2 == 0).count();
    }

    public static int[] intsFromString(String... strings){
       return Arrays.stream(strings).sequential().mapToInt(Integer::parseInt).toArray();
    }

    public static double[] randomSum(int n){
        return DoubleStream.iterate(0,x->x+Math.random()).limit(n).toArray();
    }

    public static double dotProduct(double[] v1, double[] v2){
        return IntStream.range(0, v1.length).mapToDouble(i -> v1[i]*v2[i]).sum();
    }
}
