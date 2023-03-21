import java.awt.Graphics2D;

public abstract class Token {
    
    protected Cell cell;
	
	public Token(Cell r) { //constructor which includes setter
        cell = r;
        cell.setToken(this);
    }

    public abstract void draw(Graphics2D g);
}
