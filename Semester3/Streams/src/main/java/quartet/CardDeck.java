package quartet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.function.ToIntFunction;



public class CardDeck implements Iterable<Card>{
	
	/**  The cards of the CardDeck-object. */
	private final List<Card> cards;
	

	public CardDeck() {
		this.cards = new ArrayList<Card>();
	}
	
	
	public List<Card> getCards() {
		return cards;
	}


	/**
	 * Adds a Card object to the cards attribute.
	 *
	 * @param card the card object to add
	 */
	public void addCard(Card card) {
		if(card == null) {
			throw new IllegalArgumentException("Card must not be null");
		}
		if(cards.contains(card)) {
			throw new CardDeckException("Card is already in deck");
		}
		this.cards.add(card);
	}
	
	/**
	 * Adds all cards from the given CardDeck object to this object and removes the cards from the given object.
	 *
	 * @param cardDeck the CardDeck object from which to add the cards
	 */
	public void addAllCards(CardDeck cardDeck) {
		if(cardDeck == null) 
			throw new IllegalArgumentException("cardDeck must not be null");
		Card card = cardDeck.removeFirstCard();
		while (card!=null){
			this.addCard(card);
			card = cardDeck.removeFirstCard();
		}
	}
	

	/**
	 * Shuffles the cards.
	 */
	public void shuffleCards() {
		Collections.shuffle(cards);
	}
	
	/**
	 * Sorts cards by cylinder capacity.
	 */
	public void sortByCylinderCapacity() {
		cards.sort(new CylinderCapacityComparator());
	}
	
	/**
	 * Sorts cards by cylinder count.
	 */
	public void sortByCylinderCount() {
		cards.sort(new CylinderCountComparator());
	}
	
	/**
	 * Sorts cards by horse power.
	 */
	public void sortByHorsePower() {
		cards.sort(new HorsePowerComparator());
	}
	
	/**
	 * Sorts cards by name.
	 */
	public void sortByName() {
		cards.sort(new NameComparator() );
	}
	
	
	/**
	 * Sorts cards by acceleration.
	 */
	public void sortByAcceleration() {
		cards.sort(new AccelerationComparator());
	}
	

	private static class CylinderCapacityComparator implements Comparator<Card>{

		/**
		 * Compares the the two cards by cylinder capacity DESCENDING.
		 * IMPORTANT: 
		 * returns <0 when card 1 has more cylinder capacity 
		 * returns 0 when both objects are equal
		 * returns >0 when card 2 has more cylinder capacity
		 * 
		 * when cylinder capacity is equal, the objects are compared by their natural order
		 *
		 * @param card1 the first card
		 * @param card2 the second card
		 * @return the result of the Comparator
		 */
		@Override
		public int compare(Card card1, Card card2) {	
			int result = Integer.compare(card2.getCylinderCapacity(), card1.getCylinderCapacity());
			return result==0?card1.compareTo(card2):result;
		}
	}
	

	private static class CylinderCountComparator implements Comparator<Card>{

		/**
		 * Compares the two cards by the count of cylinders DESCENDING.
		 * IMPORTANT:
		 * returns <0 when card 1 has more cylinders
		 * returns 0 when both objects are equal
		 * returns >0 when card 2 has more cylinders
		 * 
		 * when cylinder count is equal, the objects are compared by their natural order
		 *
		 * @param card1 the first card
		 * @param card2 the second card
		 * @return the result of the Comparator
		 */
		@Override
		public int compare(Card card1, Card card2) {			
			int result = Integer.compare(card2.getCylinderCount(), card1.getCylinderCount());
			return result==0?card1.compareTo(card2):result;
		}
	}

	private static class HorsePowerComparator implements Comparator<Card>{

		/**
		 * Compares the two cards by horsepower DESCENDING.
		 * IMPORTANT:
		 * returns <0 when card 1 has more horsepower
		 * returns 0 when both objects are equal
		 * returns >0 when card 2 has more horsepower
		 * 
		 * when horsepower is equal, the objects are compared by their natural order
		 *
		 * @param card1 the first card
		 * @param card2 the second card
		 * @return the result of the Comparator
		 */
		@Override
		public int compare(Card card1, Card card2) {
			int result = Integer.compare(card2.getHorsePower(), card1.getHorsePower());
			return result==0?card1.compareTo(card2):result;
		}
	}
	

	private static class AccelerationComparator implements Comparator<Card>{

		/**
		 * Compares two cards by their accelaration ASCENDING.
		 * IMPORTANT:
		 * returns -1 when card 1 is faster
		 * returns 0 when both cards are equal
		 * returns 1 when card 2 is faster
		 *
		 * @param card1 the first card
		 * @param card2 the second card
		 * @return the result of the Comparator
		 */
		@Override
		public int compare(Card card1, Card card2) {			
			int result = Integer.compare(card1.getAcceleration(), card2.getAcceleration());
			return result==0?card1.compareTo(card2):result;
		}
	}
	

	private static class NameComparator implements Comparator<Card>{

		/**
		 * Compares two cards by their name ASCENDING.
		 *
		 * @param card1 the first card
		 * @param card2 the second card
		 * @return the result of the Comparator
		 */
		@Override
		public int compare(Card card1, Card card2) {			
			int result = card1.getName().compareTo(card2.getName());
			return result==0?card1.compareTo(card2):result;
		}
	}
	
	
	
	
	/**
	 * Removes the first card of the cards and returns it.
	 *
	 * @return the first card
	 */
	public Card removeFirstCard() {
		Card card = null;
		if(!this.isEmpty()) {
			card = cards.get(0);
			cards.remove(0);
		}
		return card;
	}
	
	/**
	 * Checks if the cards attribute is empty.
	 *
	 * @return true, if cards are empty
	 */
	public boolean isEmpty() {
		return (this.cards == null || cards.isEmpty());
	}
	
	/**
	 * Returns size of the cards attribute
	 *
	 * @return the size of the cards attribute
	 */
	public int size() {
		if(!this.isEmpty()) {
			return this.cards.size();
		}
		return 0;
	}

	

	/**
	 * Returns a Iterator for the cards attribute
	 *
	 * @return the iterator
	 */
	@Override
	public Iterator<Card> iterator() {
		return new Iterator<Card>() {
			
			/**
			 * returns true as long the cards attribute contains card objects.
			 *
			 * @return true, if the cards attribute contains card objects
			 */
			@Override
			public boolean hasNext() {
				return (!cards.isEmpty());
			}

			/**
			 * Removes and returns the next first card of the cards attribute.
			 *
			 * @return the next first card of the cards attribute
			 * @throws NoSuchElementException if CardDeck is empty
			 */
			@Override
			public Card next() {
				if(!hasNext()) {
					throw new NoSuchElementException("CardDeck is empty");
				}
				
				Card card = cards.get(0);
				cards.remove(0);
				return card;
			}
			
		};
	}


	@Override
	public int hashCode() {
		return Objects.hash(cards);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CardDeck other = (CardDeck) obj;
		return Objects.equals(cards, other.cards);
	}
	
        // ******************** NEUE METHODEN ********************


	public long getAmountMinHorsePower(int horsePower){
		return cards.stream().filter(x -> x.getHorsePower()>=horsePower).count();
	}

	public double getAverageHorsePower(){
		return cards.stream().mapToDouble(Card::getHorsePower).average().orElse(-1);
	}

	public List<Card> getAccelerationBetween(int minAcc, int maxAcc){
		return cards.stream().filter(card -> card.getAcceleration() >= minAcc && card.getAcceleration() <= maxAcc)
				.sorted()
				.toList();
	}

	public Map<Integer, List<Card>> getCardsForCylinderCount(){
		return cards.stream().collect(
				Collectors.groupingBy(Card::getCylinderCount)
		);
	}

	public List<Card> getTopThreeCards(ToIntFunction<Card> attribute){
		return cards.stream().sorted((card1, card2)-> Integer.compare(attribute.applyAsInt(card2), attribute.applyAsInt(card1)))
				.limit(3)
				.toList();
	}
	public List<Card> getCardsSortedBYName(){
		return cards.stream().sorted((card1, card2)->card1.getName().compareTo(card2.getName())).toList();
	}

	public boolean existsBetterCard (Card card){
		return cards.stream().anyMatch(card1 ->{
			return card1.getAcceleration() < card.getAcceleration() && card1.getHorsePower() > card.getHorsePower() &&
					card1.getCylinderCapacity() > card.getCylinderCapacity() && card1.getCylinderCount() > card.getCylinderCount();
		});
	}

	public TradableCard getMostValuableCard(){
		return cards.stream().filter(card -> card instanceof TradableCard)
				.map(card -> (TradableCard)card)
				.max((card1,card2)-> Integer.compare(card1.getValue(), card2.getValue())).orElseThrow(IllegalStateException::new);
	}

	public Map<Integer, Long> getCylinderStatistics(){
		return cards.stream().collect(
				Collectors.groupingBy(
						Card::getCylinderCount, Collectors.counting()
				)
		);
	}

	public Map<Card, Double> getKWForCard(){
		return cards
				.stream()
				.collect(
						Collectors.groupingBy(
								card->card,
								Collectors.averagingDouble(card -> card.getHorsePower() * 0.735498750)
						)
				);
	}













	
	
	
	

}
