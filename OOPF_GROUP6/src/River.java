import java.util.ArrayList;
import java.util.Random;

public class River {

	private ArrayList<Tile> River = new ArrayList<Tile>();
	private ArrayList<Tile> currentArray;
	private ArrayList<Tile> trapArray;
	private Random rand = new Random();
	

	public void setTraps() {
		trapArray = new ArrayList<Tile>(); 
		int trapCount = rand.nextInt(9) + 1;
		while (trapArray.size() < trapCount) {
			Trap trap = new Trap();
			int index = rand.nextInt(99);
				if (taken(index, trapArray) == false) { 
					trap.setLocation(index);
					trapArray.add(trap);
				}
			
		}
		for (int i = 0; i < trapArray.size(); i++) {
			River.set(trapArray.get(i).getLocation(), trapArray.get(i));
		}
	}
	
	
	public void setCurrents() {
		currentArray = new ArrayList<Tile>();
		int currentCount = rand.nextInt(9) + 1;
		while (currentArray.size() < currentCount) { 
			Current c = new Current(); 
			int index = rand.nextInt(99);
				if (taken(index, currentArray) == false) {
					c.setLocation(index);
					currentArray.add(c);
				}
			
		}

		for (int i = 0; i < currentArray.size(); i++) {
			River.set(currentArray.get(i).getLocation(), currentArray.get(i));
		}
	}
	
	public ArrayList<Tile> getRiver() {
		return River;
	}
	

	public void createRiver() {
		for (int i=0; i < 100; i++) {
			Tile t = new Tile();
			t.setLocation(i);
			River.add(t);
		}
		
		setCurrents();
		setTraps();
	}

	public String displayRiver(ArrayList<Player> players) {
		String riverSymbols="|||";
		ArrayList<String> playerNames;
		
		for (Tile t: River) {
			boolean playerAtCurrentTile = false;
			playerNames = new ArrayList<String>();
			int currentTilePos = t.getLocation();
			
			for (Player p: players) {
				if (p.getBoatPos() == currentTilePos) {
					playerNames.add(p.getname());
					playerAtCurrentTile = true;
				}
			}
			
			if (playerAtCurrentTile) {
				riverSymbols += String.format("(%s)", String.join(",", playerNames));
				
			} else {
				riverSymbols += t.getSymbol();
			}
		}
		return riverSymbols + "|||";
	}
	
	public boolean taken(int index, ArrayList<Tile> check) {
		for (Tile t: check) {
			if (index == t.getLocation()) {
				return true;
			}
		}
		return false;
	}
}


