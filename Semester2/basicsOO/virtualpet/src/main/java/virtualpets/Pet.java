package virtualpets;

import java.util.Objects;

public class Pet {

    //declare class variables

    private final String name;
    private final PetType type;
    private  int happiness;
    private int hungriness;
    private int sleepiness;

    /**
     * Constructor
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
     *
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

    /**
     * Check if Pet is happy (happiness >=8)
     * @return true or false
     */
    public boolean isHappy(){
        return this.happiness >= 8;
    }

    /**
     * check if pet is sad (happiness <=2)
     * @return true or false
     */
    public boolean isSad(){
        return this.happiness <= 2;
    }

    /**
     * Shows sadness of Pet
     * @return sadness (10-happiness)
     */
    public int getSadness(){
        return 10-this.happiness;
    }

    //change (setter) methods

    /**
     * changes (sets) value of happiness
     * @param change value which gets added
     * @return new value
     */
    public int changeHappiness(int change){
       happiness = changeItup(happiness+change);
       return happiness;
    }

    /**
     * changes (sets) value of hungriness
     * @param change value which gets added
     * @return new value
     */
    public int changeHungriness(int change){
       hungriness =  changeItup(hungriness+change);
       return hungriness;
    }

    /**
     * changes (sets) value of sleepiness
     * @param change value which gets added
     * @return new value
     */
    public int changeSleepiness(int change){
        sleepiness = changeItup(sleepiness+change);
        return sleepiness;
    }

    /**
     * Compares two Pet Objects
      * @param other Pet object compare to
     * @return true in case they are equal, false if not
     */
    @Override
    public boolean equals(Object other){
        if(this == other) return true;
        if(other == null) return false;
        if(getClass() != other.getClass())return false;
        Pet otherPet = (Pet) other;
        if(!Objects.equals(name, otherPet.getName()))return false;
        if(!Objects.equals(type,otherPet.getType()))return false;
        if(happiness != otherPet.getHappiness())return false;
        if(hungriness != otherPet.getHungriness())return false;
        if(sleepiness != otherPet.getSleepiness())return false;
        return true;
    }

    /**
     * String representation of Pet Object
     * @return all Values of Pet as a string
     */
    @Override
    public String toString() {
       String emoji =":-|";
       if(this.isHappy())emoji=":-)";
       else if(this.isSad())emoji=":-(";
        return emoji + " " + "(" + type + ")" + " Ha: " + happiness + ", Hu: " + hungriness + ", Sl: " + sleepiness;
    }

    public void play(){
        pesHelper(2,2,2);
    }
    public void eat(){
        pesHelper(1,-2,1);
    }
    public void sleep(){
        pesHelper(-2,2,-2);
    }
    // change value of paratmers of pet
    private int changeItup(int a){
        if(a<=0)return 0;
        return Math.min(a, 10);
    }
    //helper methode to calculate new values if eat, sleep or play is invoked
    private void pesHelper( int haC, int huC, int slC){
        haC = (int) Math.round(type.getHappinesMultiplier()*haC);
        huC = (int) Math.round(type.getHungrinessMultiplier()*huC);
        slC= (int) Math.round(type.getSleepinessMultiplier()*slC);
        changeHappiness(haC);
        changeHungriness(huC);
        changeSleepiness(slC);
    }
}
