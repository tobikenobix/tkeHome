public class ShipSalvage {

    //definition of an example Map
    private final static char[][] exampleMap=new char[][]{
            //A   B   C   D   E   F   G   H   I   J
            {'O','O',' ','O',' ',' ','O','O','O','O'}, //0
            {' ',' ',' ','O',' ',' ',' ',' ',' ',' '}, //1
            {'O',' ',' ','O',' ','O','O','O',' ','O'}, //2
            {'O',' ',' ',' ',' ',' ',' ',' ',' ','O'}, //3
            {'O',' ',' ',' ',' ',' ',' ',' ',' ',' '}, //4
            {'O',' ',' ',' ',' ','O',' ',' ',' ','O'}, //5
            {' ',' ',' ',' ',' ','O',' ',' ',' ','O'}, //6
            {'O','O',' ',' ',' ',' ',' ',' ',' ','O'}, //7
            {' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}, //8
            {' ',' ',' ',' ','O','O','O','O','O','O'}, //9)
    };

    /**
     * Generates an example Map out of a previous defined two dimensional char array
     * @return two-dimensional FieldState array
     * **/

    public static FieldState[][] getExample(){
        FieldState[][] example = new FieldState[10][10];
        for(int i =0; i<10; i++){
            for(int j=0; j<10; j++) {
                example[i][j] = FieldState.fromOutput(exampleMap[i][j]);
            }
        }
        return example;
    }

    /**
     * checks if Map is valid (def. as no null entries and 10x10 dim)
     * @param map map that gets tested, has to be FieldState[][] array
     *            **/

    public static void checkValidMap(FieldState[][] map){
        if(map == null) throw new IllegalArgumentException("Map can not be null!");
        if(map[0].length!=10 || map.length!=10)throw new IllegalArgumentException("Map has to be a 10x10 field");
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                if(map[i][j]==null)throw new IllegalArgumentException("Map can not contain null entries");
            }
        }
    }

    /**
     * Prints the map to the console
     * @param map map to be printed - is an 2D FieldState Array
     * @param showHidden set to true if hidden objects should be shown, use only for testing
     */
    public static void printMap(FieldState[][] map, boolean showHidden){
        checkValidMap(map);
        String beginEnd ="+----------+";
        System.out.println(" ABCDEFGHIJ ");
        System.out.println(beginEnd);

        for(int row = 0; row<10; row++){
            System.out.print(row+1+"|");
            for(int column =0; column<10; column++){
                char output;
                if(map[row][column] == FieldState.OCCUPIED_HIDDEN && !showHidden){
                    output = ' ';
                }
                else output=map[row][column].getOutput();
                System.out.print(output);
            }
            System.out.println("|");
        }
        System.out.println(beginEnd);

    }

    /**
     * Check if Game has ended - all ships detected
     * @param map Map on which this game is played
     * @return true if all ships detected, false otherwise
     */
    public static boolean allSalvaged(FieldState map[][]){
        checkValidMap(map);
        for(int i=0; i < 10; i++){
            for(int j = 0;  j <10; j++){
                if(map[i][j]==FieldState.OCCUPIED_HIDDEN){
                    return false;
                }
            }
        }
        return true;
    }

    public static String probeField(FieldState[][]map, String field){
        checkValidMap(map);
        if(field == null) throw new IllegalArgumentException("Field can not be null!");
        char first = Character.toUpperCase(field.charAt(0));
        int scnd= Character.getNumericValue(field.charAt(1))-1;
        //check for invalid input
      if(field.contains(" ")||field.length()>2 || (first <'A'|| first >'J') || (scnd <0 || scnd>9)){
            return "Invalid input, please try again.";
        }
        else{
            int column=first - 'A';
            FieldState state = map[scnd][column];
            switch (state){
                case EMPTY -> {
                    map[scnd][column]= FieldState.MISS;
                    return "Nothing here!";
                }
                case OCCUPIED_HIDDEN -> {
                    map[scnd][column]= FieldState.OCCUPPIED_SALVAGED;
                    return "Ship found!";
                }
                case OCCUPPIED_SALVAGED, MISS -> {
                    return "You already checked this.";

                }
                default -> {
                    return "this is madness you reached this!";
                }
            }

        }
    }


    public static void main(String[] args){
        System.out.println("Hello? \n");
        //printMap(getExample(), false);
        FieldState[][] original = ShipSalvage.getExample();
        FieldState[][] map = ShipSalvage.getExample();

        ShipSalvage.probeField(map, "A1");
        original[0][0] = FieldState.OCCUPPIED_SALVAGED;

        printMap(original, false);
        printMap(map, false);
    }



}
