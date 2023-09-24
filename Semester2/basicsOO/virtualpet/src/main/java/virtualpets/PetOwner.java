package virtualpets;

import java.util.Objects;

public class PetOwner {
    private final String name;
    private final Pet firstPet;
    private final Pet secondPet;

    /**
     * Constructor for Object Pet Owner with two pets
     * @param name sets name of pet owner
     * @param firstPet Object of first Pet owned
     * @param secondPet Object of second Pet owned
     */
    public PetOwner(String name, Pet firstPet, Pet secondPet){
        if(name == null ||name.isEmpty())throw new IllegalArgumentException("Name can not be empty!");
        if(firstPet==null)throw new IllegalArgumentException("First Pet can not be empty!");
        this.name=name;
        this.firstPet=firstPet;
        this.secondPet=secondPet;
    }

    /**
     * Constructor for Object Pet Owner with one pet
     * @param name name of owner
     * @param firstPet Object of only Pet owned
     */
    public PetOwner(String name, Pet firstPet){
        this(name, firstPet, null);
    }

    /**
     * copy constructor - makes a deep copy of object other
     * @param other PetOwner object with one or two pets
     */
    public PetOwner(PetOwner other){
        if(other==null)throw new IllegalArgumentException("can not invoke copy constructor on empty object");
        this.name= other.name;
        this.firstPet = new Pet(other.getFirstPet());
        if(other.secondPet!=null){
            this.secondPet=new Pet(other.getSecondPet());
        }
        else{
            this.secondPet=null;
        }

    }


    //getter block
    public String getName() {
        return name;
    }

    public Pet getFirstPet() {
        return firstPet;
    }

    public Pet getSecondPet() {
        return secondPet;
    }

    /**
     * compares two PetOwner objects
     * @param other PetOwner object to compare to
     * @return true if objects are equals, otherwise false
     */
    @Override
    public boolean equals(Object other){
        if(this == other) return true;
        if(other == null) return false;
        if(getClass() != other.getClass())return false;
        PetOwner otherPetOwner = (PetOwner) other;
        if(!Objects.equals(otherPetOwner.getName(), name))return false;
        if(!otherPetOwner.getFirstPet().equals(firstPet))return false;
        if(!otherPetOwner.getSecondPet().equals(secondPet))return false;
        return true;
    }

    @Override
    public String toString(){
        return String.format("%s%n-First pet: %s%n Second pet:%s", name, firstPet.toString(),(secondPet != null ? secondPet : "none"));
    }

    public void takeCareOfPets(){
        helpTakingCare(firstPet);
        if(secondPet!=null)helpTakingCare(secondPet);
    }

    private void helpTakingCare(Pet pet){
        if(pet == null)throw new IllegalArgumentException("Pet can not be null");
        if(pet.getHungriness()>=pet.getSleepiness() && pet.getHungriness() >= pet.getSadness()){
            pet.eat();
        }
        else if(pet.getSleepiness()>=pet.getHungriness() && pet.getSleepiness()>=pet.getSadness()){
            pet.sleep();
        }
        else{
            pet.play();
        }
    }
}
