package bundesliga;


import java.util.HashMap;

/*
 * TODO: Ergänzen Sie die Klasse, wo angegeben. Für die Erzeugeung eines Schlüssels aus dem Namen 
 * können Sie die Methode getKeyByName() aus der Helper-Klasse verwenden. Schlüssel sollten 
 * grundsätzlich keine Leerzeichen enthalten. Schlüssel sollten auch nur aus Großbuchstaben bestehen.
 * Damit kann sichergestellt werden, dass auch ein String " fc BAyern" dem richtigen Team zugeordnet 
 * wird
 *   
 */

/**
 * The class team represents a team. Furthermore, it contains a static store of all created teams
 */ 
public class Team {

    /** the private static store of all teams. The key is a String and the value is a Team */
    private static HashMap<String, Team> teamStore = new HashMap<String, Team>();

    /** The name of the team */
    private final String name;

    /**
     * The Constructor of the team class. Checks if there is already a 
     * team whose key matches the passed name. Writes the new team in 
     * the team store.
     * 
     * @param name the name of the new team
     * @throws IllegalArgumentException if there is already a 
     * team whose key matches the passed name. 
     * @throws IllegalArgumentException if name is null or empty
     */
    public Team(String name){
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("name can not be null or empty!");
        if(teamStore.containsKey(Helper.getKeyByName(name))) throw new IllegalArgumentException("Team already in store!");
        this.name = name.trim();
        teamStore.put(Helper.getKeyByName(name), this);
        
    }

    public String getName() {
        return name;
    }

    /**
     * Returns a statement whether there is a team in the static team-store whose 
     * key matches the passed name
     * 
     * @param name the name of the team 
     * @return a statement whether there is a team in the team-store whose 
     * key matches the passed name
     * @throws IllegalArgumentException if name is null or empty
     */
    public static boolean contains(String name){
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("name can not be null or empty!");
        return teamStore.containsKey(Helper.getKeyByName(name));
    }

    /**
     * Fetches the matching team from the static team-store based on the name passed. 
     * 
     * @param name the name of the team
     * @return the team that matches the given name
     * @throws IllegalArgumentException if name is null or empty
     * @throws IllegalArgumentException if no team for the name is found
     */
    public static Team getTeam(String name){
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("name can not be null or empty!");
        if(!contains(Helper.getKeyByName(name))) throw new IllegalArgumentException("Team not in store!");
        return teamStore.get(Helper.getKeyByName(name));
    }

    /**
     * Removes a team with the given name from the team store
     * 
     * @param name the name of the team that should be removed
     */
    public static void removeTeam(String name){
        teamStore.remove(Helper.getKeyByName(name));
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Team other = (Team) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    

    


    
}
