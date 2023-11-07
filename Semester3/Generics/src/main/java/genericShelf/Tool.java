package genericShelf;

public class Tool extends ShelfItem{
    private final String name;

    public Tool(String name){
        if(name.isEmpty() || name.equals(" ")) throw new IllegalArgumentException("Name can not be empty!");
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    @Override
    public String toString(){
        return String.format("Name: %s", name);
    }
}
