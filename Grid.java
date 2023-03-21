import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Grid {

    private Cell[] cells;
	private BasicStroke stroke;
	private int cellSize;

	// Constructor
	public Grid(){
		cells = new Cell[9];
		for (int i = 0; i < 9; i++) {
			cells[i] = new Cell(); // creates cells
		}

		stroke = new BasicStroke(5);
		cellSize = 100;
	}

	public Cell[] cellHost() { // getter: to be passed to Game class
		return cells;
	}

	public void draw(Graphics gr) {
		Graphics2D g = (Graphics2D) gr; //typecasting Graphics to Graphics2D 
		


		// draw grid background
		g.setColor(Color.GRAY); //sets background color to white
		// g.fillRect(corner.x ,corner.y,cellSize*3,cellSize*3);
		g.fillRect(100, 100,300,300);

		// draw grid lines
		g.setStroke(stroke); //set line thickness
		g.setColor(Color.BLACK); //sets grid color to black
		g.drawLine(cellSize*2,cellSize, cellSize*2, cellSize*4);
		g.drawLine(cellSize*3,cellSize, cellSize*3, cellSize*4);
		g.drawLine(cellSize,cellSize*2, cellSize*4, cellSize*2);
		g.drawLine(cellSize,cellSize*3, cellSize*4, cellSize*3);

		stroke = new BasicStroke(5);
		g.setStroke(stroke); 
	}

}
