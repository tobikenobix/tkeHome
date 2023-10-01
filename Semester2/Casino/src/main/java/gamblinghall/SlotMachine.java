package gamblinghall;

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
     * Simulates customer pay-in. Updates revenue by adding cash to it
     * @param cash amount of money customer put in
     */
    public void payIn(double cash){
        if(cash<=0)throw new IllegalArgumentException("cash can not be null");
        revenue +=cash;
        profit+=cash;
    }

    /**
     * Simulates customer pay-out. Updates profit by subtracting the cash from it
     * @param cash amount of money paid out to the customer
     */
    public void payOut(double cash){
        if(cash<=0)throw new IllegalArgumentException("cash can not be null");
        profit-=cash;
    }

    public abstract double play();


}
