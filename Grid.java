import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Grid {

    private Cell[] cells;
	private BasicStroke stroke;
	private int cellSize;
	private int gridSize;
	private Point corner;

	// Constructor
	public Grid(){
		cells = new Cell[9];
		for (int i = 0; i < 9; i++) {
			cells[i] = new Cell(); // creates cells
		}
		stroke = new BasicStroke(5);
		corner = new Point(); // instantiates Point
	}

	public Cell[] cellHost() { // getter: to be passed to Game class
		return cells;
	}

	public int getCellSize() { // getter: to be passed to Game class
		cellSize = gridSize / 3;
		return cellSize;
	}

	public void resize(int w, int h) {
		if (w < h) { // condition to decide whether to use width or height to calculate gridSize
						// depending on window size
			gridSize = (int) (w * .9);
		} else {
			gridSize = (int) (h * .9);
		}
		cellSize = gridSize / 3; // formula for cellSize
		corner.x = (w - gridSize) / 2;
		corner.y = (h - gridSize) / 2;
		int num = 0; // variable to represent index number
		int yc = 0;
		for (int i = 0; i < 3; i++) { // loops to set bounds of each cell in array
			int xc = 0;
			for (int j = 0; j < 3; j++) {
				cells[num].setBounds(corner.x + xc, corner.y + yc, cellSize, cellSize);
				xc += cellSize;
				num++;
			}
			yc += cellSize;
		}
		stroke = new BasicStroke((float) (gridSize * .02)); // passess a thickness value to stroke variable
	}

	public void draw(Graphics gr) {
		Graphics2D g = (Graphics2D) gr; //typecasting Graphics to Graphics2D 
		//draws grid
		g.setStroke(stroke); //set line thickness
		g.drawLine(corner.x,corner.y+cellSize,corner.x+gridSize,corner.y+cellSize);
		g.drawLine(corner.x,corner.y+(cellSize*2),corner.x+gridSize,corner.y+(cellSize*2));
		g.drawLine(corner.x+cellSize,corner.y,corner.x+cellSize,corner.y+gridSize);
		g.drawLine(corner.x+(cellSize*2),corner.y,corner.x+(cellSize*2),corner.y+gridSize);
		//calls draw from cell class
		
		for (int i= 0; i<9; i++){
			cells[i].draw(g);
		}
		stroke = new BasicStroke(1);
		g.setStroke(stroke); //reverts line thickness to original
	}

	public int whichCell(int x, int y) {
		int result = -1; // default value for result
		for (int i = 0; i < 9; i++) { // loops through each cell to figure out which contains the point
			if (cells[i].contains(x, y)) {
				result = i; // updates the value of "result" with the cell number
			}
		}
		return result;
	}

	public void snapshot() { // prints the snapshot
		System.out.println(cells[0].toString() + cells[1].toString() + cells[2].toString());
		System.out.println(cells[3].toString() + cells[4].toString() + cells[5].toString());
		System.out.println(cells[6].toString() + cells[7].toString() + cells[8].toString());
	}

	public void clearTokens() {
		for (int i = 0; i < 9; i++) {
			cells[i].setToken(null);
		}
	}

	public boolean isFull() { // checks if the grid is full
		int c = 0;
		for (int i = 0; i < 9; i++) {
			if (cells[i].isOccupied()) { // checker
				c++;
			}
		}
		if (c == 9) { // returns true if all grids are occupied
			return true;
		} else {
			return false;
		}
	}

	public Token checkForWin() { // checks if the three designated cells have the same value
		Token t = null;
		for (int i = 0; i < 3; i++) {
			if (cells[i].equals(cells[i + 3]) && cells[i].equals(cells[i + 6])) { // checker
				t = cells[i].getToken();
			}

		}
		for (int i = 0; i < 8; i += 3) {
			if (cells[i].equals(cells[i + 1]) && cells[i].equals(cells[i + 2])) { // checker
				t = cells[i].getToken();
			}

		}
		if (cells[0].equals(cells[4]) && cells[4].equals(cells[8])) { // checker
			t = cells[4].getToken();
		}
		if (cells[2].equals(cells[4]) && cells[4].equals(cells[6])) { // checker
			t = cells[4].getToken();
		}
		return t;
	}

}
