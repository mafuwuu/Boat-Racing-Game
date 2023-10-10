import java.util.Random;

public class Tile {

	private String symbol;
	private int location;
	private int strength = 0;

	public Tile() {
		setstate("_");
	}
	
	public Tile(String symbol) {
		setstate(symbol);
	}
	
	public void setstate(String symbol) {
		this.symbol = symbol;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public void setLocation(int location) {
		this.location = location;
	}
	
	public int getLocation() {
		return location;
	}
	
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public int getStrength() {
		return strength;
	}

	public void generateStrength() {
		Random rand = new Random();
		int strengths = (rand.nextInt(3) + 1);
		setStrength(strengths);
	}
}
