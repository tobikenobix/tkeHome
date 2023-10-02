package gamblinghall;

/**
 * Enumeration for Roulette. Contains name of Roulette game types and their pay-out rate
 * @author tobias
 */
public enum RouletteGameType {
    NUMBER("Plein",36),
    RED("Rouge",2),
    BLACK("Noir",2),
    EVEN("Pair",2),
    ODD("Impair",2),
    LOW("Manque",2),
    HIGH("Passe",2),
    P12("Premiere douzaine",3),
    M12("Moyenne douzaine",3),
    D12("Derniere douzaine",3);

    private final String name;
    private final int payOutRateRoulette;

    private RouletteGameType(String name, int payOutRateRoulette){
        this.name=name;
        this.payOutRateRoulette=payOutRateRoulette;
    }

    public String getName() {
        return name;
    }

    public int getPayOutRateRoulette() {
        return payOutRateRoulette;
    }
}
