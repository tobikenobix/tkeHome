package virtualpets;

/**
 * enum that models four pet types
 * @author tobias
 * @version 0.1
 */
public enum PetType {
    DOG(2,2,2),
    CAT(1,3,2),
    BIRD(0.75,1,0.75),
    RABBIT(0.5,0.5,1);

    private final double happinesMultiplier;
    private final double hungrinessMultiplier;
    private final double sleepinessMultiplier;

    /**
     * Constructor creates those four animales
     */
    private PetType(double happinesMultiplier, double hungrinessMultiplier, double sleepinessMultiplier){
        this.happinesMultiplier=happinesMultiplier;
        this.hungrinessMultiplier=hungrinessMultiplier;
        this.sleepinessMultiplier=sleepinessMultiplier;
    }

    //getter block

    public double getHappinesMultiplier() {
        return happinesMultiplier;
    }

    public double getHungrinessMultiplier() {
        return hungrinessMultiplier;
    }

    public double getSleepinessMultiplier() {
        return sleepinessMultiplier;
    }
}
