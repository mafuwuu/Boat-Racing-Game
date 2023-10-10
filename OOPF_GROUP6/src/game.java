import java.util.ArrayList;
import java.util.Scanner;

public class game {
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private Dice dice = new Dice();
	private River river = new River();
	private Database results = new Database();
	private static Scanner input = new Scanner(System.in);
	
	public static void gui(){
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~Main Menu~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		//choice
		while(true){
			System.out.println("1. Play Game");
			System.out.println("2. How to play?");
			System.out.println("3. View LeaderBoard");
			System.out.println("4. Exit Game");
			System.out.print("Enter your choice [1, 2, 3, OR 4]: ");

			switch(input.next()){
			case "1":
				game game1 = new game();
				game1.start();
				break;

			case "2":
				System.out.println("Printing Tutorial...");
				tutorial();

			case "3":
				System.out.println("Printing LeaderBoard...");
				leaderboard();
				break;
				
			case "4":
				System.out.println("Exiting Game...");
				System.exit(0);
				break;
				
			default:
				System.out.println("Invalid input!");
				break;

			}
		}
	}
	//tutorial
	private static void tutorial(){
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~~~~~~~~~~~~~~~HOW TO PLAY~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("\n");
		System.out.println("The goal of the game is to get the highest score.");
		System.out.println("\n");
		System.out.println("The game will be played with one or two players.");
		System.out.println("\n");
		System.out.println("Once the game started, all the traps and currents will be scattered randomly in the river.");
		System.out.println("\n");
		System.out.println("Some currents are stronger than the others, so as the traps. The stronger current or trap will make the boat moves more steps forward or backward.");
		System.out.println("\n");
		System.out.println("When boat hits the trap, the boat will need to move backward x number of steps, when the boat hits the current, it will move forward x number of steps.");
		System.out.println("\n");
		System.out.println(" Game will end when either player's boat reaches the end of the river.");
		System.out.println("\n");
		System.out.println("Press enter to go back to the menu.");
		input.nextLine();
		input.nextLine();
		gui();
	}
	//leaderboard
	private static void leaderboard(){
		Database results = new Database();
		results.loadDB();
		results.printDB();
		System.out.println("Press enter to go back to the menu.");
		input.nextLine();
		input.nextLine();
		gui();
	}
	public void start() {
		int round = 1;		
		setupDatabase();
		
		// Creating river
		river.createRiver();
		ArrayList<Tile> riverTiles = river.getRiver();
		
		getPlayerDetails();
		
		System.out.println("Game started!\n");
		System.out.println(river.displayRiver(new ArrayList<Player>()));
		
		while (!finished()) {
			System.out.printf("%d ROUND(S) HAVE PASSED\n", round);
			
			for (Player currentPlayer: players){
				int moveBy = currentPlayer.rollDice(dice);			
				currentPlayer.setBoatPos(currentPlayer.getBoatPos() + moveBy);			
				int currentPos = currentPlayer.getBoatPos();				
				Tile currentTile = riverTiles.get(currentPos);
				
				if (currentTile instanceof Current){
					currentTile.generateStrength();
					currentPlayer.setBoatPos(currentPlayer.getBoatPos() + currentTile.getStrength());
                    System.out.println("You have stumbled upon a trap. You have been pushed back " + currentTile.getStrength() + " spaces.");					
				} 
				else if (currentTile instanceof Trap){
					currentTile.generateStrength();
					currentPlayer.setBoatPos(currentPlayer.getBoatPos() - currentTile.getStrength());
                    System.out.println("You have stumbled upon a trap. You have been pushed back " + currentTile.getStrength() + " spaces.");				
				}
				currentPlayer.setUserScore(currentPlayer.getUserScore() + 1);
				System.out.printf("%s moved to %d.\n", currentPlayer.getname(), currentPlayer.getBoatPos() + 1);
			}
			System.out.println();
			System.out.println("Displaying current board...");
			System.out.println(river.displayRiver(players));
			round++;
		}
		playAgain();
	}
	
	
	public boolean finished() {
		for (Player p: players) {
			if (p.getBoatPos() == 99) {
				System.out.println("Game finished!");
				System.out.printf("%s won with %d moves", p.getname(), p.getUserScore());
				results.addResults(p);
				results.storeDB();
				results.printDB();
				return true;
			}
		}
		return false;
	}
	
	public void getPlayerDetails() {
		int NumP = 0;	
		while (true){
			try{
				System.out.print("How many players are playing [1 OR 2]: ");
				NumP = input.nextInt();
				if (NumP == 1 || NumP == 2){
					break;
				} else {
					throw new Exception();
				}
			}
			catch(Exception e){
				System.err.println("\nOnly 1 OR 2 Players!");
				input.nextLine();
			}
		}
		for (int i = 0; i < NumP; i++) {
			Player p = new Player();
			System.out.printf("Enter name for Player %d: ", i + 1);
			p.setname(input.next());
			players.add(p);
		}
		System.out.println();
	}
	
	
	public void setupDatabase() {
		results.createDB();
		results.loadDB();
		results.printDB();
	}
	
	
	public void playAgain() {
		System.out.print("Would you like to try again [y/n]: ");
		String tryAgain = input.next();
		
		while ((tryAgain.equals("y") == false) && (tryAgain.equals("n")== false)) {
			System.out.print("Invalid input!");
			System.out.print("\nWould you like to try again [y/n]: ");
			tryAgain = input.next();				
		}
		
		if (tryAgain.equals("y")) {
			System.out.println();
			players = new ArrayList<Player>();
			start();
			
		} else {
			System.out.print("\nThank you very much for playing!\n");
			System.out.println();
		}
	
	}
}
