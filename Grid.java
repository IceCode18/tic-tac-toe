import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

public class Grid {

	
	// Field
	private Cell[] cells;
	private BasicStroke stroke;
	private int cellSize, gridSize, margin;
	private Point corner;

	private final Color PRIMARY_COLOR = new Color(42, 45, 49);
	private final Color SECONDARY_COLOR = new Color(52, 55, 59);
	private final int BOARD_COLUMNS = 3;
	private final double MARGIN_MULTIPLIER = 0.05;
	private final double GRID_SIZE_MULTIPLIER = 0.9;

	// Constructor
	public Grid() {
		cells = new Cell[9];
		for (int i = 0; i < 9; i++) {
			cells[i] = new Cell(); // instantiates grid cells
		}
		corner = new Point(); // instantiates Point
	}

	// Getter: to be passed to Game class
	public Cell[] cellHost() { 
		return cells;
	}

	// Getter: to be passed to Game class
	public int getCellSize() { 
		cellSize = gridSize / 3;
		return cellSize;
	}

	// Method to make grid responsive
	public void resize(int w, int h) {
		if (w < h) { // condition to decide whether to use width or height depending on window size to calculate gridSize 
			gridSize = (int) (w * GRID_SIZE_MULTIPLIER);
		} else {
			gridSize = (int) (h * GRID_SIZE_MULTIPLIER);
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
		margin = (int) (cellSize * MARGIN_MULTIPLIER);

		Graphics2D g = (Graphics2D) gr;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw the background
		RoundRectangle2D background = new RoundRectangle2D.Double(corner.x, corner.y, cellSize * BOARD_COLUMNS,
				cellSize * BOARD_COLUMNS, (margin * 2), (margin * 2));
		g.setColor(PRIMARY_COLOR);
		g.fill(background);

		// Draw the cells 
		g.setPaint(PRIMARY_COLOR);
		stroke = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g.setStroke(stroke);
		for (int row = 0; row < BOARD_COLUMNS; row++) {
			for (int col = 0; col < BOARD_COLUMNS; col++) {
				int x = col * cellSize + corner.x;
				int y = row * cellSize + corner.y;

				RoundRectangle2D cell = new RoundRectangle2D.Float(x + margin, y + margin, cellSize - (margin * 2),
						cellSize - (margin * 2), (margin * 2), (margin * 2));
				g.setColor(SECONDARY_COLOR);
				g.fill(cell);

				// Draw the bevels
				g.setPaint(new Color(255, 255, 255, 50));
				g.drawLine(x + margin, y + cellSize - margin, x + cellSize - margin, y + cellSize - margin);
				g.drawLine(x + cellSize - margin, y + margin, x + cellSize - margin, y + cellSize - margin);

				g.setPaint(new Color(0, 0, 0, 60));
				g.drawLine(x + margin, y + cellSize - margin, x + margin, y + margin);
				g.drawLine(x + margin, y + margin, x + cellSize - margin, y + margin);
			}
		}

		// Draw the tokens
		for (int i = 0; i < 9; i++) {
			cells[i].draw(g);
		}

		stroke = new BasicStroke(margin, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		g.setStroke(stroke); // reverts line thickness to original
		g.setColor(Color.WHITE);
	}

	// Returns the cell containing the point
	public int whichCell(int x, int y) {
		int result = -1; // default value for result
		for (int i = 0; i < 9; i++) { // loops through each cell to figure out one which contains the point
			if (cells[i].contains(x, y)) {
				result = i; // updates the value of "result" with the cell number
			}
		}
		return result;
	}

	// Prints the snapshot
	public void snapshot() {
		System.out.println(cells[0].toString() + cells[1].toString() + cells[2].toString());
		System.out.println(cells[3].toString() + cells[4].toString() + cells[5].toString());
		System.out.println(cells[6].toString() + cells[7].toString() + cells[8].toString());
		System.out.println("");
	}

	// Resets all cell tokens to null
	public void clearTokens() {
		for (int i = 0; i < 9; i++) {
			cells[i].setToken(null);
		}
	}

	// Checks if the board is full
	public boolean isFull() {
		int c = 0;
		for (int i = 0; i < 9; i++) {
			if (cells[i].isOccupied()) {
				c++;
			}
		}
		if (c == 9) {
			return true;
		} else {
			return false;
		}
	}

	// Method to check if a player has won
	public Token checkForWin() {
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
