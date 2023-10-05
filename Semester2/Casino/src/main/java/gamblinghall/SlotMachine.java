package gamblinghall;

import java.util.Objects;

/**
 * implementation of Slot Machine. Provides Methods and attributes used by all other machines.
 * @author tobias
 */
public abstract class SlotMachine {
    private final String name;
    private double revenue;
    private double profit;
    private int countGames;
    private int countWins;

    /**
     * Default constructor, creates new SlotMachine. Initializes all values except name to zero
     * @param name Name of the SlotMachine, can not be empty
     */
    public SlotMachine(String name){
        if(name == null ||name.isEmpty()) throw new IllegalArgumentException("Name can not be empty!");
        this.name = name;
        this.revenue = 0;
        this.profit = 0;
        this.countGames = 0;
        this.countGames = 0;
        this.countWins = 0;
    }

    //####### Block of getter methods


    public String getName() {
        return name;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getProfit() {
        return profit;
    }

    public int getCountGames() {
        return countGames;
    }

    public int getCountWins() {
        return countWins;
    }

    // #### getter block end

    /**
     * Simulates customer pay-in. Updates revenue by adding cash to it. Increments countGames
     * @param cash amount of money customer put in
     */
    public void payIn(double cash){
        if(cash<=0)throw new IllegalArgumentException("cash can not be null");
        revenue +=cash;
        profit+=cash;
        //stake has to be put in, so we can increment here and don't need to use a separate method
        countGames++;
    }

    /**
     * Simulates customer pay-out. Updates profit by subtracting the cash from it. Increments countWins
     * @param cash amount of money paid out to the customer
     */
    public void payOut(double cash){
        if(cash<=0)throw new IllegalArgumentException("cash can not be null");
        profit-=cash;
        //can be done here since a payOut only comes with a win
        countWins++;
    }

    /**
     * abstract methode implemented in the extended classes. Used to implement the game logic of the machine.
     * @return double value of money
     */
    public abstract double play(double stake);

    /**
     * String representation of SlotMachine
     * @return attribute name of the machine
     */
    @Override
    public String toString(){
        return this.name;
    }

    /**
     * Compares two SlotMachines by name and class
     * @param o Object to compare
     * @return true if equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlotMachine that = (SlotMachine) o;
        return Objects.equals(name, that.name);
    }

    /**
     * Generates HashCode based on attribute name
     * @return hash value
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
