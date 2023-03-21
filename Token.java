import java.awt.Graphics2D;

public abstract class Token {
    
    // Field
    protected Cell cell;
	
    // Constructor
	public Token(Cell r) {
        cell = r;
        cell.setToken(this);
    }

    public abstract void draw(Graphics2D g);
}
