package quartet;

import java.util.Objects;

/**
 * The Class Player.
 */
public class Player {
	
	
	/** The name of the player. */
	private final String name;
	
	/** The cards of the player. */
	private final CardDeck cards;
	
	/** The currently played card. */
	private Card currentCard;
	
	/** The flag for human players */
	private final boolean human;



	public Player(String name) {
		this(name,false);
	}
	

	public Player(String name, boolean human) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Name must not be null or empty");
		this.name = name;
		cards = new CardDeck();
		currentCard = null;
		this.human = human;
	}

	
	public CardDeck getCards() {
		return cards;
	}

	
	public String getName() {
		return name;
	}

	
	
	public boolean isHuman() {
		return human;
	}
	
	
	public Card getCurrentCard() {
		return currentCard;
	}
	

	public void setCurrentCardNull() {
		this.currentCard = null;
	}
	

	/**
	 * Removes first card of cards, sets it as currentCard and returns it
	 *
	 * @return the removed first card
	 */
	public Card playCard() {
		if(this.hasCards()) {
			currentCard=cards.removeFirstCard();
		}else {
			currentCard = null;
		}
		return currentCard;	
	}
	
	/**
	 * Checks if player has cards
	 *
	 * @return true, if player has cards
	 */
	public boolean hasCards() {
		return !this.cards.isEmpty();
	}

	

	
	/**
	 * Checks if player has won the current round.
	 *
	 * @param winnerCard the winner card
	 * @return true, winner card equals currently played card (currentCard)
	 */
	public boolean hasWon(Card winnerCard) {
		if(this.currentCard!=null && this.currentCard.equals(winnerCard)) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(human, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return human == other.human && Objects.equals(name, other.name);
	}
	
	
	
	
	
	

}
