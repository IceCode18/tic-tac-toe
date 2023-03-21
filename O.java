import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class O extends Token{
    
    private Rectangle rec;
    private int margin;
    
    public O(Cell c){
        super(c);
        rec = c.getBounds(); //gets rectangle bounds values
    }

    // Overriding the toString() method
    @Override
    public String toString(){
        return "O";
    }

    @Override
    public void draw(Graphics2D g) {
        margin = (int) (rec.width * 0.15);
        g.setColor(Color.ORANGE);
		g.drawOval(rec.x+margin,rec.y+margin,rec.width-(margin*2),rec.width-(margin*2));
    }
}
