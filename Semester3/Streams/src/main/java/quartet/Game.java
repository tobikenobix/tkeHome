package quartet;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class Game {
	
	/**  The players of the game. */
	private final List<Player> players;
	
	/** The card deck played in the game. */
	private final CardDeck cardDeck;
	
	/** The cards played in the current round. */
	private CardDeck playedCards;
	
	/**  The counter for the rounds. */
	private int round = 0;
	
	/**  The winner of the current round. */
	private Player roundWinner;
	

	public Game(CardDeck cardDeck,  List<Player> players ) {
		if(cardDeck == null)
			throw new IllegalArgumentException("cardDeck must not be null");
		if(players == null || players.size() < 2) 
			throw new IllegalArgumentException("players must contain at least zwo objects of the type Player");
		if(this.countHumanPlayers(players)>1)
			throw new IllegalArgumentException("players may contain at most one human player.");
		if((players.size()) >= cardDeck.size())
			throw new IllegalArgumentException("the card deck must contain at least one card for each player");
		
		
		cardDeck.shuffleCards();
		this.cardDeck = cardDeck;
		this.players = players;
	}

	
	/**
	 * Distributes the cards to the players in turn. 
	 * The cardDeck attribute of this object should be empty after the distribution.
	 */
	public void distributeCards(){
		Iterator<Card> cardsIterator = cardDeck.iterator();
		while(cardsIterator.hasNext()) {
			for(Player player:this.players) {
				if(cardsIterator.hasNext()) {
					player.getCards().addCard(cardsIterator.next());
				}
			}
		}
	}
	
	/**
	 * Starts the game. No further method is called from outside after instantiation.
	 */
	public void play() {
		distributeCards();
		
		while(!isGameOver()) {
			round++;
			collectCards();
			printIntro();
			playGameType(getGameType());
			roundWinner.getCards().addAllCards(playedCards);
			//set all current cards = null
			for(Player player:players) {
				player.setCurrentCardNull();
			}
		}
		
		if(roundWinner.isHuman()) {
			System.out.println("Sie haben das Spiel gewonnen!");
		}else {
			System.out.println("\n\n" + roundWinner.getName() + " hat das Spiel gewonnen!");
		}
		
		
	}
	
	private int countHumanPlayers(List<Player> players) {
		int counter = 0;
		if(players == null || players.size() == 0) {
			return 0;
		}
		for(Player player: players) {
			if (player.isHuman())
				counter++;
		}
		return counter;
	}
		
	/**
	 * Returns the human player if a human player is in the game and null otherwise.
	 *
	 * @return the human player
	 */
	public Player getHumanPlayer() {
		for(Player player:this.players) {
			if(player.isHuman())
				return player;
		}
		return null;
	}
	
	/**
	 * Checks if a human player is in the game.
	 *
	 * @return true, if a human player is in the game.
	 */
	public boolean hasHumanPlayer() {
		if(getHumanPlayer() == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Counts players with cards.
	 *
	 * @return the int
	 */
	private int countPlayersWithCards() {
		int count = 0;
		for(Player player:this.players) {
			if(player.hasCards()) 
				count ++;
		} 
		return count;
	}
	
	/**
	 * Checks if there is more than one player with cards in the game.
	 *
	 * @return true, if game is over (only one player with cards is left)
	 */
	public boolean isGameOver() {
		if (countPlayersWithCards() > 1) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * Plays the game depending on the selected card attribute.
	 *
	 * @param gameType the selected card attribute
	 */
	private void playGameType(int gameType) {
		if(gameType < 0 && gameType >3) 
			throw new IllegalArgumentException("gameType must be between 0 and 3");
		
		
		switch(gameType) {
		case 0: 
			System.out.println("Gespielt wird: Hubraum");
			this.playedCards.sortByCylinderCapacity();
			break;
		case 1: 
			System.out.println("Gespielt wird: Zylinder");
			this.playedCards.sortByCylinderCount();
			break;
		case 2:
			System.out.println("Gespielt wird: PS");
			this.playedCards.sortByHorsePower();
			break;
		case 3: 
			System.out.println("Gespielt wird: 0 - 100 km/h");
			this.playedCards.sortByAcceleration();
			break;
		}
		
		//the winner card
		Card winnerCard = playedCards.getCards().get(0);
		
		//get the winner of the round
		for(Player player:this.players) {
			if(player.hasWon(winnerCard)) {
				roundWinner = player;
				break;
			}
		}
		
		//output
		if(roundWinner.isHuman()) {
			System.out.println("Sie haben die Runde gewonnen!");
		}else {
			System.out.printf("%s hat die Runde gewonnen. Seine Karte war: %n%s%n", roundWinner.getName(), winnerCard);
		}
		
		
	}
	
	/**
	 * Outputs the opening of the game on the terminal.
	 */
	private void printIntro() {
		System.out.printf("%n%n----------------------------------%nRunde %d%n",this.round);
		Player humanPlayer = this.getHumanPlayer();
		if (humanPlayer != null) {
			if (humanPlayer.hasCards() || humanPlayer.getCurrentCard()!=null) {
				//size of cards + currentCard
				System.out.println("Sie haben noch " + (humanPlayer.getCards().size()+1) + " Karten");
			} else {
				System.out.println("Sie haben keine Karten mehr");
			}
		}

	}
	
	/**
	 * Collects the first card of each player.
	 */
	private void collectCards() {
		playedCards = new CardDeck();
		for(Player player:players) {
			if(player.hasCards()) {
				playedCards.addCard(player.playCard());
			}
		}	
	}
	
	
	
	/**
	 * Returns the game type of the next game. If the human player still has cards, he determines the game type.
	 *
	 * @return the game type
	 */
	private int getGameType() {
		Player humanPlayer = this.getHumanPlayer();
		if(humanPlayer == null || !(humanPlayer.hasCards() || humanPlayer.getCurrentCard()!=null)) {
			return (int)((Math.random()) * 3 + 1);
		}else {
			
			Scanner input = new Scanner(System.in);
			
			System.out.printf("Ihre aktuelle Karte: %n%n %s %n%n",humanPlayer.getCurrentCard());
			int gameType = -1;
			while(gameType<0 || gameType>3) {
				System.out.println("Welche Eigenschaft wollen Sie spielen? \n" +
								"0: Hubraum \n"+
								"1: Zylinder \n"+
								"2: PS \n" + 
								"3: 0 - 100 km/h: \n");
				gameType = input.nextInt();
			}
			return gameType;
		}
	}
	
	

}
