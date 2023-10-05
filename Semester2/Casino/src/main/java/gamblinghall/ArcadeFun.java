package gamblinghall;

public class ArcadeFun extends SlotMachine {
    private final int stake;

    //### getter block
    public int getStake(){
        return this.stake;
    }
    //###

    public ArcadeFun(String name){
        super(name);
        this.stake = 1;
    }
    public ArcadeFun(String name, int stake){
        super(name);
        if(stake<1 || stake > 10)
            throw new IllegalArgumentException("stake has to be between 1 and 10.");
        this.stake=stake;
    }

    /**
     * simulates play of a fun machine - increments revenue and  profit by stake
     * @param stake Stake that user puts in - has to be same as stake from class
     * @return 0 since customer can not win money
     */
    @Override
    public double play(double stake){
        if(stake != this.stake)
            throw new IllegalArgumentException("Stakes have to be the same");
        payIn(stake);
        return 0;
    }

    @Override
    public String toString(){
        return String.format("%s made us %f profit", this.getName(), this.getProfit());
    }
}
