public class Player extends MainBoat{
    private int UserScore;
    private MainBoat boat;
    private String name;

    //Constructor
    public Player(){
        this.UserScore = 0;
        this.boat = new MainBoat();
        this.name = "";
    }

    public Player(String name, int score){
        this.name = name;
        this.UserScore = score;
    }

    //Setter, Getter
    public void setname(String name){
        this.name = name;
    }
    public String getname(){
        return this.name;
    }
    public void setUserScore(int score){
        this.UserScore = score;
    }
    public int getUserScore(){
        return this.UserScore;
    }

    public void setBoatPos(int pos){
        boat.setPos(pos);
    }
    public int getBoatPos(){
        return boat.getPos();
    }

    //Method
    public int rollDice(Dice dice){
        return Dice.DiceRoller();
    }

    //toString
    public String toString(){
        return "Player: " + this.name + " Score: " + this.UserScore;
    }
}