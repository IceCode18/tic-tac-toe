import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class O extends Token{
    
    // Field
    private Rectangle rec;
    private int margin;
    private int strokeWidthOuter, strokeWidthInner;

    private final Color TOKEN_SHADOW = new Color(255, 255, 255, 100);
    private final Color TOKEN_COLOR = new Color(250, 81, 2, 255);
    private final double STROKE_OUTER_MULTIPLIER = 0.1;
    private final double STROKE_INNER_MULTIPLIER = 0.08;
    private final double MARGIN_MULTIPLIER = 0.15;

    // Constructor
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
        strokeWidthOuter = (int) (rec.width * STROKE_OUTER_MULTIPLIER); 
		strokeWidthInner = (int) (rec.width * STROKE_INNER_MULTIPLIER);
		margin = (int) (rec.width * MARGIN_MULTIPLIER);

		// Draw O shadow
		g.setStroke(new BasicStroke(strokeWidthOuter, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.setPaint(TOKEN_SHADOW);
		g.drawOval(rec.x+margin,rec.y+margin,rec.width-(margin*2),rec.width-(margin*2)); 

		// Draw O shape
		g.setStroke(new BasicStroke(strokeWidthInner, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.setPaint(TOKEN_COLOR);
		g.drawOval(rec.x+margin,rec.y+margin,rec.width-(margin*2),rec.width-(margin*2)); 
    }
}
