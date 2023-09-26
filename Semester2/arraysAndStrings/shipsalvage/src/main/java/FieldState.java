/**
 * @author tobias
 * Enumeration for field states of the game
 */
public enum FieldState {
    EMPTY(' '),
    MISS('X'),
    OCCUPIED_HIDDEN('O'),
    OCCUPPIED_SALVAGED('#');
    private final char output;

    FieldState(char output){
        this.output=output;
    }
    public char getOutput(){
        return this.output;
    }

    /**
     * Methode to get FieldState for given char
     * @param output char to convert to value
     * @return corresponding FieldState
     */
    public static FieldState fromOutput(char output){
     for (FieldState state : values()){
         if(state.getOutput()==output) {
             return state;
         }
     }
     throw new IllegalArgumentException("no FieldState for that value");
    }

}
