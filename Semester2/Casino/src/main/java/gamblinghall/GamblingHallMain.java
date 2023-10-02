package gamblinghall;

public class GamblingHallMain {
    public static void main(String[] args){
        var DS1 = new DoubleShot("DS1", 5);
        var DS2 = new DoubleShot("DS2");

        for(int i =0; i<100000; i++){
            DS1.play(5);
            DS2.play(1);
        }
        System.out.println(DS1.printInfo());
        System.out.println(DS2.printInfo());

    }

}
