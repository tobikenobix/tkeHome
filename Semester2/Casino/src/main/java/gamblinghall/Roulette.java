package gamblinghall;

import java.util.Arrays;
import java.util.Objects;
import java.util.TreeSet;

public class Roulette extends SlotMachine implements GamblingMachine{
    private RouletteGameType rouletteGameType;
    private int betNumber;

    /**
     * First constructor, sets RouletteGameType to Black
     * @param name name of the Roulette machine
     */
    public Roulette(String name){
        super(name);
        this.rouletteGameType=RouletteGameType.BLACK;
    }

    /**
     * Second constructor only game type can be set
     * @param name Name of the Roulette machine
     * @param rouletteGameType type of game which gets played. Can not be NUMBER
     */
    public Roulette(String name, RouletteGameType rouletteGameType){
        super(name);
        if(rouletteGameType == null || rouletteGameType == RouletteGameType.NUMBER)
            throw new IllegalArgumentException("Game type can not be null or number!");
        this.rouletteGameType = rouletteGameType;
    }

    /**
     * Third constructor only Number game type
     * @param name Name of the Roulette machine
     * @param betNumber int number to bet on
     */
    public Roulette(String name, int betNumber){
        super(name);
        if(betNumber<0 || betNumber > 36)
            throw new IllegalArgumentException("betNumber has to be between 0 and 36!");
        this.rouletteGameType = RouletteGameType.NUMBER;
        this.betNumber = betNumber;
    }

    //### getter/setter block

    public RouletteGameType getRouletteGameType() {
        return rouletteGameType;
    }

    public void setRouletteGameType(RouletteGameType rouletteGameType) {
        if(rouletteGameType == null)
            throw new IllegalArgumentException("Game Type can not be null!");
        this.rouletteGameType = rouletteGameType;
    }

    public int getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(int betNumber) {
        if(betNumber <0 || betNumber > 36)
            throw new IllegalArgumentException("Bet number can only be between 0 and 36");
        this.betNumber = betNumber;
    }

    /**
     * Game logic of Roulette - determits if user has won selected mode or not
     * @param stake double value. stake set by the user
     * @return Pay-out for customer if won or 0 if customer lost
     */
    @Override
    public double play(double stake){
        //stake has to be greater 0
        if(stake<=0 || stake >10) throw new IllegalArgumentException("Stake can not be zero in Roulette!");
        //Random number generator to set winning number between 0 and 36
        int randRoulette = (int)(Math.random()*36);
        payIn(stake);

        //define winning numbers where an array is necessary
        final Integer[] rouge = {1,3,5,7,9,12,14,16,18,19,21,23,25}; //red numbers
        final TreeSet<Integer> rougets = new TreeSet<>(Arrays.asList(rouge));
        final Integer[] noir ={2,4,6,8,10,11,13,15,17,20,22,24}; //black numbers
        final TreeSet<Integer>noirts=new TreeSet<>(Arrays.asList(noir));
        //var to check if game is won and customer gets a payout
        boolean getPayOut=false;
        //determine which mode was selected and check for win accordingly
        switch(rouletteGameType){
            case NUMBER -> {
                getPayOut=this.betNumber == randRoulette;
            }
            case RED -> {
                getPayOut =rougets.contains(randRoulette);
            }
            case BLACK -> {
                   getPayOut = noirts.contains(randRoulette);
            }
            case EVEN -> {
                getPayOut = randRoulette%2==0;
            }
            case ODD -> {
                getPayOut = (randRoulette%2==1);
            }
            case LOW -> {
                getPayOut= randRoulette <=18 && randRoulette >=1;
            }
            case HIGH -> {
                getPayOut = randRoulette <=36 && randRoulette >= 19;
            }
            case P12 -> {
                getPayOut = randRoulette <=12 && randRoulette>=1;
            }
            case M12 -> {
                getPayOut = randRoulette <=24 && randRoulette >=13;
            }
            case D12 -> {
                getPayOut = randRoulette <=36 && randRoulette >=25;
            }
        }
        if(getPayOut){
            double x = rouletteGameType.getPayOutRateRoulette()*stake;
            payOut(x);
            return x;
        }
        return 0;
    }

    @Override
    public double getRealPayOutRate(){
        return ((this.getRevenue() -  this.getProfit())/this.getRevenue());
    }

    /**
     * String representation of Roullete
     * @return name and game mode as String
     */
    @Override
    public String toString(){
        if(Objects.equals(rouletteGameType.getName(), "Plein"))
            return String.format("Roulettemachine %s: You are playing %s(%d)",this.getName(),rouletteGameType.getName(),this.getBetNumber());
        else
            return String.format("Roulettemachine %s: You are playing %s",this.getName(),rouletteGameType.getName());
    }

    /**
     * Prints info about current state of the game
     * @return String representation of revenue and profit
     */
    public String printInfo(){
        return String.format("%s: Revenue: %.2f \nProfit: %.2f \nReal pay-out-rate: %.2f", this.getName(), this.getRevenue(), this.getProfit(), this.getRealPayOutRate());
    }

}
