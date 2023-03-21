import java.awt.Graphics2D;

public class X extends Token{
    
    public X(Cell c){
        super(c);
    }

    // Overriding the toString() method  
    @Override
    public String toString(){
        return "X";
    }

    @Override
    public void draw(Graphics2D g) {
        // TODO Auto-generated method stub
    }
}
