package virtualpets;

public class VirtualPetMain {
    public static void main(String[] args){
        Pet rosco = new Pet("Rosco", PetType.DOG);
        Pet morgana = new Pet("Morgana", PetType.CAT);
        Pet rabbit = new Pet("Rabbit of Caerbannog", PetType.RABBIT);

        System.out.println(rosco.toString());
        System.out.println(morgana.toString());
        System.out.println(rabbit.toString());

        Pet roscoClone=new Pet(rosco);
        System.out.println(roscoClone.equals(rosco));
    }
}