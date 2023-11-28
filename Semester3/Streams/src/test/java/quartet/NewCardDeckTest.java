package quartet;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

public class NewCardDeckTest {

  // card1  "Ford Granada Ghia 3.0"    2993  6 138 11
  // card10 "Mercedes 450 SL"          4520  8 225  9  20
  // card11 "Panther J72 V12"          5343 12 269  6
  // card12 "Moteverdi Palm Beach"     7207  8 305  6 150
  // card13 "Buick Century"            5657  8 155 12
  // card14 "Oldsmobile 98 Regency"    7325  8 190 11 100
  // card15 "Pontiac Firebird Formula" 6473  8 185 10
  // card2  "Opel Diplomat V8"         5354  8 230 10
  // card3  "BWM 3.3 Li"               3295  6 200  9  50
  // card4  "Mercedes 450 SEL 6.9"     6834  8 286  8
  // card5  "Rolls-Royce Camargue"     6750  8 200 12  10
  // card6  "Rover 3500 S"             3470  8 141 11
  // card7  "Lotus Elite 502"          1973  4 150  8  80
  // card8  "Jaguar XJ-S"              5343 12 287  7
  // card9  "Alfa Romeo 2000 Spider"   1962  4 131 11

  private CardDeck cd;
  private  Card card1 ;
  private  Card card2 ;
  private  Card card3 ;
  private  Card card4 ;
  private  Card card5 ;
  private  Card card6 ;
  private  Card card7 ;
  private  Card card8 ;
  private  Card card9 ;
  private  Card card10;
  private  Card card11;
  private  Card card12;
  private  Card card13;
  private  Card card14;
  private  Card card15;

  @Before
  public void getFilledCardDeck() {
    cd = new CardDeck();
    card1  = new Card("Ford Granada Ghia 3.0",         2993, 6,  138, 11);
    card2  = new Card("Opel Diplomat V8",              5354, 8,  230, 10);
    card3  = new TradableCard("BWM 3.3 Li",            3295, 6,  200, 9,  50);
    card4  = new Card("Mercedes 450 SEL 6.9",          6834, 8,  286, 8);
    card5  = new TradableCard("Rolls-Royce Camargue",  6750, 8,  200, 12, 10);
    card6  = new Card("Rover 3500 S",                  3470, 8,  141, 11);
    card7  = new TradableCard("Lotus Elite 502",       1973, 4,  150, 8,  80);
    card8  = new Card("Jaguar XJ-S",                   5343, 12, 287, 7);
    card9  = new Card("Alfa Romeo 2000 Spider",        1962, 4,  131, 11);
    card10 = new TradableCard("Mercedes 450 SL",       4520, 8,  225, 9,  20);
    card11 = new Card("Panther J72 V12",               5343, 12, 269, 6);
    card12 = new TradableCard("Moteverdi Palm Beach",  7207, 8,  305, 6,  150);
    card13 = new Card("Buick Century",                 5657, 8,  155, 12);
    card14 = new TradableCard("Oldsmobile 98 Regency", 7325, 8,  190, 11, 100);
    card15 = new Card("Pontiac Firebird Formula",      6473, 8,  185, 10);

    cd.addCard( card1  );
    cd.addCard( card2  );
    cd.addCard( card3  );
    cd.addCard( card4  );
    cd.addCard( card5  );
    cd.addCard( card6  );
    cd.addCard( card7  );
    cd.addCard( card8  );
    cd.addCard( card9  );
    cd.addCard( card10 );
    cd.addCard( card11 );
    cd.addCard( card12 );
    cd.addCard( card13 );
    cd.addCard( card14 );
    cd.addCard( card15 );

  }

  @Test
  public void testGetAmountMinHorsePower(){

    assertEquals("getAmountMinHorsePower must return 15 for 50 PS ",
        15, cd.getAmountMinHorsePower(50));

    assertEquals("getAmountMinHorsePower must return 10 for 175 PS ",
        10, cd.getAmountMinHorsePower(175));

    assertEquals("getAmountMinHorsePower must return 8 for 200 PS ",
        8, cd.getAmountMinHorsePower(200));

    assertEquals("getAmountMinHorsePower must return 0 for 400 PS ",
        0, cd.getAmountMinHorsePower(400));
  }

  @Test
  public void testGetAverageHorsePower(){
    double avgHP = cd.getAverageHorsePower();

    //(138 + 230 + 200 + 286 + 200 + 141 + 150 + 287 + 131 + 225 + 269 + 305 + 155 + 190 + 185) / 15
    assertEquals( "getAverageHorsePower must return average value of all HP values",
        206.133, avgHP, 1e-2);

  }
  
  @Test
  public void testGetAverageHorsePowerEmpty(){
    double avgHP = new CardDeck().getAverageHorsePower();

    assertEquals( "getAverageHorsePower must return -1 for an empty card deck",
        -1, avgHP, 1e-10);

  }

  @Test
  public void getAccelerationBetween(){

    List<Card> l = cd.getAccelerationBetween(8,10);

    assertNotNull("return value of getAccelerationBetween must not be null", l);
    assertEquals("getAccelerationBetween must return the right amount of cards",
        6, l.size());

    assertTrue("return value of getAccelerationBetween must contain card2", l.contains(card2));
    assertTrue("return value of getAccelerationBetween must contain card3", l.contains(card3));
    assertTrue("return value of getAccelerationBetween must contain card4", l.contains(card4));
    assertTrue("return value of getAccelerationBetween must contain card7", l.contains(card7));
    assertTrue("return value of getAccelerationBetween must contain card10", l.contains(card10));
    assertTrue("return value of getAccelerationBetween must contain card15", l.contains(card15));

    l = cd.getAccelerationBetween(20, 30);
    assertEquals("getAccelerationBetween must return empty list for 20,30", 0, l.size());

    List<Card> l2 = cd.getAccelerationBetween(0, 100);
    assertEquals("getAccelerationBetween must return all 15 cards for 0, 100", 15, l2.stream().distinct().toList().size());
    assertTrue("getAccelerationBetween must return all 15 cards for 0, 100", 
        cd.getCards().stream().allMatch(card -> l2.contains(card)));


  }
  
  @Test
  public void testGetCardsForCylinderCount(){
    Map<Integer, List<Card>> m = cd.getCardsForCylinderCount();

    assertNotNull("getCardsForCylinderCount must not return null");

    assertEquals("there are 4 different cylinders counts",
        4, m.size());

    assertTrue("must contain an entry for 6 cylinders", m.containsKey(6));
    assertEquals("there are two cards with 6 cylinders",
        2, m.get(6).size());
    assertTrue("must contain card1 for 6 cylinders", m.get(6).contains(card1));
    assertTrue("must contain card1 for 6 cylinders", m.get(6).contains(card3));

    assertTrue("must contain an entry for 8 cylinders", m.containsKey(8));
    assertEquals("there are two cards with 6 cylinders",
        9, m.get(8).size());
    assertTrue("must contain card2 for 6 cylinders", m.get(8).contains(card2));
    assertTrue("must contain card4 for 6 cylinders", m.get(8).contains(card4));
    assertTrue("must contain card5 for 6 cylinders", m.get(8).contains(card5));
    assertTrue("must contain card6 for 6 cylinders", m.get(8).contains(card6));
    assertTrue("must contain card10 for 6 cylinders", m.get(8).contains(card10));
    assertTrue("must contain card12 for 6 cylinders", m.get(8).contains(card12));
    assertTrue("must contain card13 for 6 cylinders", m.get(8).contains(card13));
    assertTrue("must contain card14 for 6 cylinders", m.get(8).contains(card14));
    assertTrue("must contain card15 for 6 cylinders", m.get(8).contains(card15));



  }


//    @Test
//    public void testGetCardsSortedbyName(){
//
//      List<Card> l = cd.getCardsSortedByName();
//
//      assertNotNull("getCardsSortedByName must not return null", l);
//
//      assertEquals("getCardsSortedByName must return all cards sorted by name",
//          l, List.of(card9 , card3 , card13, card1 , card8 , card7 , card4 , card10, card12, card14, card2 , card11, card15, card5 , card6));
//
//    }

    @Test
    public void testGetTopThreeCards(){

      List<Card> l = cd.getTopThreeCards(Card::getCylinderCount);

      assertNotNull("getTopThreeCards must not return null", l);
      assertEquals("top three cards wrt cylinder count must contain exactly three cards",
          3, l.size());

      assertTrue("top three cards wrt cylinder count must contain cards 8 and 11",
          l.contains(card8) && l.contains(card11));

      
      assertTrue("top three cards wrt cylinder count must contain a single card with 8 cylinders",
          l.stream().anyMatch(c -> c.getCylinderCount()==8));

      l = cd.getTopThreeCards(Card::getHorsePower);

      assertEquals("top three cards wrt horse power must contain exactly three cards",
          3, l.size());

      assertEquals("top three cards wrt to horse powewr must be cards 12, 8, 4 in that order",
          l, List.of(card12, card8, card4));

      cd = new CardDeck();
      cd.addCard(card1);
      cd.addCard(card2);

      l = cd.getTopThreeCards(Card::getHorsePower);

      assertEquals("top three cards wrt to horse power must contain exactly two cards (if the deck contains only two cards)",
          2, l.size());


    }

    @Test 
    public void existsBetterCard(){

      assertTrue("there exists a better card than card7",
          cd.existsBetterCard(card7));

      assertFalse("there exists no better card than card14",
          cd.existsBetterCard(card14));

    }

    @Test
    public void testGetMostValuableCard(){
      TradableCard c = cd.getMostValuableCard();

      assertNotNull("getMostValuableCard must not return null", c);
      assertEquals("getMostValuableCard return card 12", card12, c);

      cd = new CardDeck();
      cd.addCard(card1);
      cd.addCard(card15);
      cd.addCard(card11);

      assertThrows("getMostValuableCard must throw IllegalArgumentException if there is no TradableCard",
          IllegalStateException.class,
          () -> cd.getMostValuableCard());

    }

    
    @Test
    public void testGetCylinderStatistics(){
      Map<Integer, Long> m = cd.getCylinderStatistics();

      assertNotNull("getCylinderStatistics must not return null", m);
      assertEquals("there are 4 different amounts of cylinder counts", 
          4, m.size());

      assertTrue("there a 2 cards with 4 cylinders ",
          m.containsKey(4) && m.get(4) == 2);

      assertTrue("there a 2 cards with 6 cylinders ",
          m.containsKey(6) && m.get(6) == 2);

      assertTrue("there a 2 cards with 6 cylinders ",
          m.containsKey(6) && m.get(6) == 2);

      assertTrue("there a 9 cards with 8 cylinders ",
          m.containsKey(8) && m.get(8) == 9);

      assertTrue("there a 2 cards with 12 cylinders ",
          m.containsKey(12) && m.get(12) == 2);

    }

    @Test
    public void testGetKWForCard(){
      Map<Card,Double> m = cd.getKWForCard();

      assertNotNull("getKWForCard must not return null", m);
      assertEquals("getKWForCard must return exactly 15 entries", 15, m.size());

      assertTrue( "getKWForCard must return an entry for card1", m.containsKey(card1));
      assertEquals( "the entry for card 1 must be 138*0.73549875=101.4988275",
          101.4988275, m.get(card1), 1e-5);

      assertTrue( "getKWForCard must return an entry for card5", m.containsKey(card5));
      assertEquals( "the entry for card 5 must be 200*0.73549875=147.09975",
          147.09975, m.get(card5), 1e-5);

    }


  
}
