package quartet;

import java.util.Objects;


public class Card implements Comparable<Card>{
	
	/** The name attribute of the card. */
	private final String name;
	
	/** The cylinder capacity attribute of the card. */
	private final int cylinderCapacity;
	
	/** The cylinder count attribute of the card. */
	private final int cylinderCount;
	
	/** The horse power attribute of the card. */
	private final int horsePower;
	
	/** The acceleration attribute of the card. */
	private final int acceleration;

	
	public Card(String name, int cylinderCapacity, int cylinderCount, int horsePower, int acceleration) {
		
		if(name == null || name.isEmpty()) 
			throw new IllegalArgumentException("Name must not be null or empty");
		if(cylinderCapacity <= 0)
			throw new IllegalArgumentException("cylinder capacity must be greater than 0");
		if(cylinderCount <= 0)
			throw new IllegalArgumentException("cylinder count must be greater than 0");
		if(horsePower <= 0)
			throw new IllegalArgumentException("horsepower must be greater than 0");
		if(acceleration <= 0)
			throw new IllegalArgumentException("acceleration must be greater than 0");
		
		this.name = name;
		this.cylinderCapacity = cylinderCapacity;
		this.cylinderCount = cylinderCount;
		this.horsePower = horsePower;
		this.acceleration = acceleration;
	}
	

	
	public String getName() {
		return name;
	}


	public int getCylinderCapacity() {
		return cylinderCapacity;
	}


	public int getCylinderCount() {
		return cylinderCount;
	}


	public int getHorsePower() {
		return horsePower;
	}


	public int getAcceleration() {
		return acceleration;
	}	



	/**
	 * Compares two cards by their natural order. The natural order is:
	 * 1. acceleration ascending
	 * 2. horse power descending
	 * 3. cylinder capacity descending
	 * 4. cylinder count descending
	 * 5. name ascending
	 *
	 * @param o the other object, this object is compared to
	 * @return the result of the comparison
	 */
	@Override
	public int compareTo(Card o) {
		if(this.equals(o)) {
			return 0;
		}
		
		int result = Integer.compare(this.getAcceleration(), o.getAcceleration());
		result = result==0?Integer.compare(o.getHorsePower(),  this.getHorsePower()):result;
		result = result==0?Integer.compare(o.getCylinderCapacity(),  this.getCylinderCapacity()):result;
		result = result==0?Integer.compare(o.getCylinderCount(),  this.getCylinderCount()):result;
		result = result==0?this.getName().compareTo(o.getName()):result;
		
		return result;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(acceleration, cylinderCapacity, cylinderCount, horsePower, name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return acceleration == other.acceleration && cylinderCapacity == other.cylinderCapacity
				&& cylinderCount == other.cylinderCount && horsePower == other.horsePower
				&& Objects.equals(name, other.name);
	}


	@Override
	public String toString() {
		return "+++ " + this.getName() + " +++" + System.lineSeparator() +
				"Hubraum:\t" + this.getCylinderCapacity() + System.lineSeparator() +
				"Zylinder:\t" + this.getCylinderCount() + System.lineSeparator() +
				"PS:\t\t" + this.getHorsePower() + System.lineSeparator()+
				"0 - 100 km/h:\t" + this.getAcceleration();
	}
	
	
	
	

}
