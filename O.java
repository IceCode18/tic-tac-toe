import java.awt.Graphics2D;

public class O extends Token{
    
    public O(Cell c){
        super(c);
    }

    // Overriding the toString() method
    @Override
    public String toString(){
        return "O";
    }

    @Override
    public void draw(Graphics2D g) {
        // TODO Auto-generated method stub
    }
}
