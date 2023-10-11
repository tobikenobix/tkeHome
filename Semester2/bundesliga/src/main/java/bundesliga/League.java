package bundesliga;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*
 * TODO: Ergänzen Sie die Klasse, wo angegeben. Für die Erzeugung eines Schlüssels aus dem Namen 
 * können Sie die Methode getKeyByName() aus der Helper-Klasse verwenden. Schlüssel sollten 
 * grundsätzlich keine Leerzeichen enthalten. Schlüssel sollten auch nur aus Großbuchstaben bestehen.
 * Damit kann sichergestellt werden, dass auch ein String " b UNDES LIGA" der richtigen Liga zugeordnet 
 * wird
 *   
 */

/** represents a league. Furthermore, it contains a static store of all created leagues */ 
public class League {

    /** the private static store of all leagues. The key is a String and the value is a league */

    private static HashMap<String, League> leagueStore = new HashMap<>();
    
    /** The name of the league */
    private String name;

    /** The teams playing in the league */
    /*
     * Jedes Team darf nur ein einziges mal in der Collection vorkommen.
     * Die Sortierung ist dabei irrelevant
     */
    private HashSet<Team> teams = new HashSet<Team>();
    

    /** The games played in the league */
    /*
     * Jedes Game darf nur ein einziges mal in der Collection vorkommen.
     * Die Sortierung ist dabei sehr wichtig. Implementieren Sie hierfür 
     * auch die natürliche Ordnung von Game (vgl Game).
     */  
    private TreeSet<Game> games = new TreeSet<Game>();

    /**
     * The Constructor of the team League. Checks if there is already a 
     * league whose key matches the passed name. Writes the new league in 
     * the league store.
     * 
     * @param name the name of the new team
     * @throws IllegalArgumentException if there is already a 
     * league whose key matches the passed name. 
     * @throws IllegalArgumentException if name is null or empty
     * @throws IllegalArgumentException if league with the given name already exists
     */
    public League(String name){
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("name can not be null or empty!");
        if(leagueStore.containsKey(Helper.getKeyByName(name))) throw new IllegalArgumentException("League already exists");
        this.name = Helper.getKeyByName(name);
        leagueStore.put(Helper.getKeyByName(name), this);
        
    }

    /**
     * Adds a team to the local set teams
     * 
     * @param team the team to add
     * @throws IllegalArgumentException if team is null
     * @throws IllegalArgumentException in team is already in Set
     */
    public void addTeam(Team team){
        if(team == null || teams.contains(team)) throw new IllegalArgumentException("team null or already in store!");
        teams.add(team);
    }

    /**
     * Fetches a team from the static team-store based on the name passed
     * and calls addTeam(Team team) method
     * 
     * @param name the name of the team
     */
    public void addTeam(String name){

        if(name == null || name.isEmpty()) throw new IllegalArgumentException("name can not be null or empty!");
        addTeam(Team.getTeam(name));
    }

    /**
     * Adds a game to the local set games
     * 
     * @param game the game to add
     * @throws IllegalArgumentException if game is null
     * @throws IllegalArgumentException in game is already in Set
     */
    public void addGame(Game game){
        if(game == null || games.contains(game)) throw new IllegalArgumentException("game can not be null or already be in set!");
        games.add(game);
        
    }

    

    /**
     * Fetches the matching team from the static team-store based on the name passed. 
     * Checks if team is in local Set teams. If not IllegalArgumentException is thrown.
     * 
     * @param name the name of the team
     * @return the team that matches the name given name
     * @throws IllegalArgumentException if name is null or empty
     * @throws IllegalArgumentException if no team for the name is found
     * @throws IllegalArgumentException if team is not meber of the local Set teams
     */
    public Team getTeam(String name){

        if(name == null || name.isEmpty()) throw new IllegalArgumentException("name can not be null or empty!");
        if(!teams.contains(Team.getTeam(name))) throw new IllegalArgumentException("Team not in team list!");
        return Team.getTeam(name);
        
    }

    
    public Table getTable(){
        return getTable(null);
    }

    public Table getTable(Integer matchDay){
        return getTable(matchDay, Table.TableType.BOTH);
    }

    public Table getTable(Integer matchDay, Table.TableType type){
        return new Table(this, matchDay, type);
    }

    public String getName() {
        return name;
    }

    /**
     * Returns a statement whether there is a league in the static league-store whose 
     * key matches the passed name
     * 
     * @param name the name of the league 
     * @return a statement whether there is a league in the league-store whose 
     * key matches the passed name
     * @throws IllegalArgumentException if name is null or empty
     */
    public static boolean containsLeague(String name){
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("name can not be null or empty!");
        return leagueStore.containsKey(name);
        
    }

    /**
     * Fetches the matching league from the static league-store based on the name passed. 
     * 
     * @param name the name of the league
     * @return the league that matches the given name
     * @throws IllegalArgumentException if name is null or empty
     * @throws IllegalArgumentException if no league for the name is found
     */
    public static League getLeague(String name){

        if(name == null || name.isEmpty()) throw new IllegalArgumentException("name can not be null or empty!");
        if(!containsLeague(name)) throw new IllegalArgumentException("name is not available!");
        return leagueStore.get(name);
    }

    /**
     * Removes a league with the given name from the static league-store
     * 
     * @param name the name of the league that should be removed
     */
    public static void removeLeague(String name){

        leagueStore.remove(name);
    }

    /**
     * returns a unmodifiable Copy of the local Set teams
     * 
     * @return a unmodifiable Copy of the local Set teams
     */
    public Set<Team> getTeams() {

        return Set.copyOf(teams);
    }

    /**
     * returns a unmodifiable Copy of the local Set games
     * 
     * @return a unmodifiable Copy of the local Set games
     */
    public Set<Game> getGames() {

        return Set.copyOf(games);
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
        League other = (League) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
 
}
