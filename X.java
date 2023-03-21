import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class X extends Token{
    
    private Rectangle rec;
    private int margin;
    private int lineLength;
    
    public X(Cell c){
        super(c);
        rec = c.getBounds(); //gets rectangle bounds values
    }

    // Overriding the toString() method
    @Override
    public String toString(){
        return "X";
    }

    @Override
    public void draw(Graphics2D g) {
		margin = (int) (rec.width * 0.15);
		lineLength = rec.width - margin;
		g.setColor(Color.BLUE);
		g.drawLine(rec.x + margin, rec.y + margin, rec.x + lineLength, rec.y + lineLength);
		g.drawLine(rec.x + lineLength, rec.y + margin, rec.x + margin, rec.y + lineLength);
    }

}
