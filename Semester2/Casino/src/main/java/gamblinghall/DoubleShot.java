package gamblinghall;

/**
 * Implementation of a Double shot machine. Is a SlotMachine
 * @author tobias
 */
public class DoubleShot extends SlotMachine{
    //stake player sets per game
    private final int stake;
    //current jackpot, gets increased by 0,1 every game
    private double jackpot;
    //amount of jackpot wins
    private int countJackPot;

    //### getter block
    public int getStake() {
        return stake;
    }

    public double getJackpot() {
        return jackpot;
    }

    public int getCountJackPot() {
        return countJackPot;
    }
    //### end getter block

    /**
     * First constructor - player can decide stake
     * @param name Name of the DoubleShot Machine
     * @param stake value between 1 and 10
     */
    public DoubleShot(String name, int stake){
        super(name);
        if(stake<=0 || stake > 10) throw new IllegalArgumentException("Stake has to be between 1 and 10!");
        this.stake=stake;
    }

    /**
     * Second constructor - stake is always set to 1
     * @param name Name of the DoubleShot Machine
     */
    public DoubleShot(String name){
        super(name);
        this.stake = 1;
    }

    /**
     * Play logic for DoulbeShot. Customer has a 0.1% chance of winning jackpot. "normal" Win doubles the stake. Jackpot gets increased by 10% of stake everytime.
     * @param stake Doulbe value to insert stake. Has to be the same as initialized in constructor
     * @return double win
     */
    @Override
    public double play(double stake){
        //compare if stakes are equal
        if((int) stake != this.stake) throw new IllegalArgumentException("Stakes have to be equal");
        this.payIn(stake);
        //jackpot gets increased by 10% of stake per game
        jackpot += this.stake *0.1;
        //jackpot probability and pay out rate set to fix terms to conform german gambling law
        double jckptProb=0.001;
        double payOutRate=0.92;
        //value that gets paid out to customer
        double win = 0;
        //random number to see if user won or not
        double rndm = Math.random();

        //check if customer won jackpot - if so pay it out, reset jackpot to zero and increase jackpot win
        if(rndm <= jckptProb){
            win = jackpot;
            jackpot =0;
            countJackPot++;
            payOut(win);
        }
        //adapt winning scenario so payOutRate gets pleased jackpot rate = 0.1; factor of a win = 2(*stake)
        else if(rndm <=(payOutRate - 0.1)/2){
            win = stake *2;
            payOut(win);
        }

        return win;
    }

    /**
     * Prints information about game statistics
     * @return string of amount of revenue, profit, jackpot hits
     */
    public String printInfo(){
        return String.format("%s: Revenue: %f \nProfit: %f\nJackpot hit: %d", this.getName(), this.getRevenue(), this.getProfit(), this.getCountJackPot());
    }
}
