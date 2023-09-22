package virtualpets;

public class Pet {

    //declare class variables

    private final String name;
    private final PetType type;
    private  int happiness;
    private int hungriness;
    private int sleepiness;

    /**
     * Constructor
     * @return Pet object with values name & type - default values: happiness = 5, hungriness = 3, sleepiness = 1
     * @param name Name of the Pet object
     * @param type Pet Type
     *
     */
    public Pet(String name, PetType type){
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("Name can not be empty!");
        this.name=name;
        if(type == null ) throw  new IllegalArgumentException("Type can not be null");
        this.type=type;
        this.happiness = 5;
        this.hungriness = 3;
        this.sleepiness = 1;
    }

    /**
     * Copy constructor
     * @return Copy of Pet object
     */
    public Pet(Pet other){
        if(other == null)throw new IllegalArgumentException("Pet object can not be null!");
        this.name=other.name;
        this.type=other.type;
        this.happiness=other.happiness;
        this.hungriness=other.hungriness;
        this.sleepiness=other.sleepiness;
    }


    //getter methodes


    public String getName() {
        return name;
    }

    public PetType getType() {
        return type;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getHungriness() {
        return hungriness;
    }

    public int getSleepiness() {
        return sleepiness;
    }
}
