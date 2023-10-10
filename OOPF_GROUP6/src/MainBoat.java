public class MainBoat {
    private int position;

    public MainBoat(){
        this.position = 0;
    }

    public void setPos(int pos){
        if (pos >99){
            this.position = 99;
        } else if (pos < 0){
            this.position = 0;
        } else {
            this.position = pos;
        }
    }
    public int getPos(){
        return this.position;
    }

    public String toString(){
        return String.format("Position: %d", getPos());
    }

}
