package quartet;

import java.util.ArrayList;

public class QuartetMain {

	public static void main(String[] args) {

		CardDeck tolleAutos = getFilledCardDeck();
		Player humanPlayer = new Player("Hansi",true);
		
		ArrayList<Player> players= new ArrayList<Player>();
		players.add(humanPlayer);
		players.add(new Player("Spieler 2"));
		players.add(new Player("Spieler 3"));
		players.add(new Player("Spieler 4"));
		Game spiel = new Game(tolleAutos, players);
		spiel.play();
		
		

	}

	
	private static CardDeck getFilledCardDeck() {
		CardDeck cd = new CardDeck();
		cd.addCard(new Card("Ford Granada Ghia 3.0",2993,6,138,11));
		cd.addCard(new Card("Opel Diplomat V8",5354,8,230,10));
		cd.addCard(new TradableCard("BWM 3.3 Li",3295,6,200,9, 50));
		cd.addCard(new Card("Mercedes 450 SEL 6.9",6834,8,286,8));
		cd.addCard(new TradableCard("Rolls-Royce Camargue",6750,8,200,12,10));
		cd.addCard(new Card("Rover 3500 S", 3470,8,141,11));
		cd.addCard(new TradableCard("Lotus Elite 502",1973,4,150,8, 80));
		cd.addCard(new Card("Jaguar XJ-S",5343,12,287,7));
		cd.addCard(new Card("Alfa Romeo 2000 Spider",1962,4,131,11));
		cd.addCard(new TradableCard("Mercedes 450 SL",4520,8,225,9, 20));
		cd.addCard(new Card("Panther J72 V12",5343,12,269,6));
		cd.addCard(new TradableCard("Moteverdi Palm Beach",7207,8,305,6,150));
		cd.addCard(new Card("Buick Century",5657,8,155,12));
		cd.addCard(new TradableCard("Oldsmobile 98 Regency",7325,8,190,11,100));
		cd.addCard(new Card("Pontiac Firebird Formula",6473,8,185,10));
		
		
		return cd;
	}

}
