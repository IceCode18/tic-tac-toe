import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class X extends Token{
    
    // Field
    private Rectangle rec;
    private int margin;
    private int lineLength;
    private int strokeWidthOuter, strokeWidthInner;

    private final Color TOKEN_SHADOW = new Color(255, 255, 255, 100);
    private final Color TOKEN_COLOR = new Color(0, 0, 255, 255);
    private final double STROKE_OUTER_MULTIPLIER = 0.1;
    private final double STROKE_INNER_MULTIPLIER = 0.08;
    private final double MARGIN_MULTIPLIER = 0.15;
    
    // Constructor
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
		strokeWidthOuter = (int) (rec.width * STROKE_OUTER_MULTIPLIER); 
		strokeWidthInner = (int) (rec.width * STROKE_INNER_MULTIPLIER);
		margin = (int) (rec.width * MARGIN_MULTIPLIER);
		lineLength = rec.width - margin;

		// Draw X shadow
		g.setStroke(new BasicStroke(strokeWidthOuter, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.setPaint(TOKEN_SHADOW);
		g.drawLine(rec.x + margin, rec.y + margin, rec.x + lineLength, rec.y + lineLength);
		g.drawLine(rec.x + lineLength, rec.y + margin, rec.x + margin, rec.y + lineLength);

		// Draw X shape
		g.setStroke(new BasicStroke(strokeWidthInner, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.setPaint(TOKEN_COLOR);
		g.drawLine(rec.x + margin, rec.y + margin, rec.x + lineLength, rec.y + lineLength);
		g.drawLine(rec.x + lineLength, rec.y + margin, rec.x + margin, rec.y + lineLength);
    }

}
