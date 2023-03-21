

public class Cell {
    
    // Field
    private Token token;

    //Constructor
    public Cell(){
        token = null;
    }

    // setter for token
    public void setToken(Token t){
		token = t;
	}

    // getter for token
	public Token getToken(){
		return token;
	}

    // checks if cell is occupied by a token
    public boolean isOccupied(){ 
        return (token != null);
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
