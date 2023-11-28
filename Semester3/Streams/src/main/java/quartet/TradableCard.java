package quartet;

public class TradableCard extends Card {

  private int value;

  public TradableCard(String name, int cylinderCapacity, int cylinderCount, int horsePower, int acceleration, int value) {
    super(name, cylinderCapacity, cylinderCount, horsePower, acceleration);

    if (value < 0)
      throw new IllegalArgumentException("value must not be negative");

    this.value = value;
  }

  public int getValue(){
    return value;
  }

  @Override
  public String toString(){
    return super.toString() + System.lineSeparator() + "Value:\t" + this.getValue();

  }
  

}
