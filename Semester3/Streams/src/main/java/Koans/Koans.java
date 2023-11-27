package Koans;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * class used for some stream koans. Including a play-around with the collatz series.
 */
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

    public static Map<Integer, List<String>> stringsForLength(Collection<String> strings){
       return strings.stream().collect(Collectors.groupingBy(String::length));
    }

    public static LongStream collatzSeries(int start){
        return LongStream.iterate(start, x -> (x%2==0)?(x/2):(x*3+1));
    }

    public static LongStream collatzTruncated(int start){
        Predicate<Long> p = duplicateChecker();
        return collatzSeries(start).takeWhile(x -> !p.test(x));
    }

    public static <T> Predicate<T> duplicateChecker(){
        HashSet<T> alreadyCalled = new HashSet<>();
        return x -> !alreadyCalled.add(x);
    }

    public static boolean collatzOrbit(int start){
        return collatzTruncated(start).anyMatch(x -> x==1);
    }

    public static boolean collatzTrueForLimit(int limit){
        return IntStream.range(1,limit).allMatch(x -> collatzOrbit(x));
    }

}
