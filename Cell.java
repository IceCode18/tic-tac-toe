import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Cell {
    
    // Field
    private Token token;
    private Rectangle bounds;

    //Constructor
    public Cell(){
        token = null;
        bounds = new Rectangle(); //instantiates Rectangle class
    }

    // Setter for token
    public void setToken(Token t){
		token = t;
	}

    // Getter for token
	public Token getToken(){
		return token;
	}

    // Setter to setBounds of the cells
    public void setBounds(int x, int y, int w, int h){
		bounds.setBounds(x,y,w,h); 
    }

    // Getter whose value is to be used by Token subclasses
	public Rectangle getBounds(){ 
		return bounds;
	}

    // Finds out if the cell contains a coordinate
    public boolean contains(int x, int y){
		if(bounds.contains(x,y)){
			return true;
		}
		else{
			return false;
		}
	}

    // Checks if cell is occupied by a token
    public boolean isOccupied(){ 
        return (token != null);
    }

    // Draws token
    public void draw(Graphics2D g){ 
		if(token!=null){ //to avoid null pointer exceptions
			token.draw(g); 
		}
	}

    // Overriding the toString() method
    @Override
	public String toString(){
		if(token==null){
			return " ";
		}
		else{
			return token.toString();
		}
	}

    // Overriding the equals() method
    @Override
    public boolean equals(Object o){
        if(o instanceof Cell){
            Cell other = (Cell) o;
            if(this.token != null && other.token!= null){
                return this.token.toString().equals(other.token.toString());
            }
        }
        return false;
    }

}
