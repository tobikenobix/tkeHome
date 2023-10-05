package gamblinghall;

/**
 * Interface for GamblingMachine only - Methods that guarantee satisfaction of gambling law
 */
public interface GamblingMachine {
    /**
     * Determines the real pay out rate with data provided by the slot machines
     * @return real pay out rate as double
     */
    public double getRealPayOutRate();
}
