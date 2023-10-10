import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Database {

	private Scanner in;
	private Formatter out;
	private ArrayList<Player> players;
	

	public void createDB() {
		createFile("TopScore.txt");
	}
	
	public void loadDB() {
		players = new ArrayList<Player>();
		in = openInputFile("TopScore.txt");
		readResultsFile();
		closeInputFile(in);
	}
	
	public void storeDB(){
		out = openOutputFile("TopScore.txt");
		writeResultsFile();
		closeOutputFile(out);
	}
	
	public void printDB() {
		sortResults();
		printResults();
	}
	
	public void createFile(String filename) {
		try{
			File file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
		}
		catch (IOException e) {
			System.out.println("Error creating file.");
            e.printStackTrace();
		}
	}
	
	public Scanner openInputFile(String filename) {
		try
		{
			in = new Scanner(new File(filename));
		}
		catch (FileNotFoundException fileNotFoundException)
		{
			System.err.println("Error opening file.");
			fileNotFoundException.printStackTrace();
		}
		return in;
	}
	

	public void readResultsFile(){
		try
		{
			while(in.hasNext())
			{
				Player newplayer = new Player();
				newplayer.setname(in.next());
				newplayer.setUserScore(in.nextInt());
				players.add(newplayer);
			}
		}
		catch (NoSuchElementException elementException)
		{
			System.err.println("File improperly formed.");
			in.close();
			System.exit(1);
		}
		catch (IllegalStateException stateException)
		{
			System.err.println("Error reading from file.");
			System.exit(1);
		}
		
	}
	

	public void closeInputFile(Scanner input) {
		if (input != null) {
			input.close();
		}
	}
	
	public Formatter openOutputFile(String filename) {
		Formatter temout = null;
		try
		{
			temout = new Formatter (new File(filename));
		}
		catch (FileNotFoundException fileNotFoundException)
		{
			System.err.println("Error opening file. File not found");
			System.exit(1);
		}
		return temout;
	}
	
	public void writeResultsFile()
	{
		for (int j = 0; j < players.size(); j++) {
			out.format("%s %d\n", players.get(j).getname(), players.get(j).getUserScore());
		}
	}
	
	public void closeOutputFile(Formatter output) {
		if (output != null) {
			output.close();
		}
	}

	public void addResults(Player p1)
	{
		Player winner = p1;

		try
		{
			players.add(winner);
		}
		catch (FormatterClosedException formatterClosedException)
		{
			System.err.println("Error writing to file.");
			return;
		}
		catch (NoSuchElementException elementException)
		{
			System.err.println("invalid input");
			return;
		}
	}
	

	public void sortResults() {
		Comparator<Player> scoreComparator = (p1, p2) -> (int) (p1.getUserScore() - p2.getUserScore());
		Collections.sort(players, scoreComparator);
	}
	
	public void printResults() {
		int size;
		System.out.println();
		System.out.println("LEADERBOARD");
		System.out.println("Position      Name       Score");
		if (players.size() < 5)
		{
			size = players.size();
		}
		else {
			size = 5;
		}
		for (int i = 0; i < size; i++) {
			System.out.printf("%4d.          %-10s  %-5d\n", i+1, players.get(i).getname(), players.get(i).getUserScore());
		}
		System.out.println();
	}
	
	
}
