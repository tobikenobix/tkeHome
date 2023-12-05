package threadrace;

public class ThreadRace {
    private static int raceCounter;
    private static void threadHelper(int adder, Object locker){
        while(!Thread.interrupted()) {
            fib(35);
            synchronized (locker) {
                raceCounter += adder;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        final Object locker= new Object();
        raceCounter = 0;
        Thread threadA = new Thread(()->threadHelper(1, locker));
        Thread threadB = new Thread(()-> threadHelper(-1, locker));
        Thread.sleep(10000);
        threadA.interrupt();
        threadB.interrupt();
        threadA.join();
        threadB.join();
        System.out.println(raceCounter);

    }

    private static int fib(int n) {
        return (n <= 1 ? n : fib(n - 1) + fib(n - 2));
    }
}
