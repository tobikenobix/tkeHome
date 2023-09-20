package randomNumber;

import java.util.Scanner;

import static java.lang.System.exit;

public class PseudoRandomNumber {
    public final static long MODULUS = (long) (Math.pow(2,31)-1);
    public final static long MULTIPLIER = 48271;
    public static void main(String[] args){
        long y;
        // ########## user input block ##########
        // create scanner to read user input
        Scanner scan = new Scanner(System.in);
        //ask user for amount of numbers and assign to amount
        System.out.println("Please enter amount of numbers you want to generate: ");
        int amount =scan.nextInt();
        if(amount < 0){
            System.out.println("you can not create negative amount of numbers!");
            return;
        }
        //ask user for seed and assign to x
        System.out.println("Please enter your seed: ");
        long x = scan.nextInt();
        if(x <= 0){
            System.out.println("Seed has to be greater 0");
            return;
        }
        //ask user for range
        System.out.println("Enter your minimal value (included): ");
        long min = scan.nextInt();
        System.out.println("Enter the maximal value (excluded): ");
        long max =scan.nextInt();
        // ###########End user input block######

        //calculate pseudo random number according to lehmer scheme
        for(int i = 0; i<amount; i++){
            x=(x*MULTIPLIER)%MODULUS;
            //System.out.println(x);
            //adjust number for range
            y=(x % (max-min))+min;
            System.out.println(y);
        }

    }
}

