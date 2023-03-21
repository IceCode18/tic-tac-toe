import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel implements MouseListener, MouseMotionListener{
	
	// Field
    private Grid grid;
	private ArrayList<Point> gesture;
	private int down, dx, dy;
	private Cell host;
	private Cell[] cells;
	private int same;
	
	private final double GESTURE_LENGTH_MULTIPLIER = 0.3;
	private final Color BACKGROUND_COLOR = new Color(15, 15, 17);
	private final Color BACKGROUND_COLOR2 = new Color(255, 255, 255);

	// Constructor
	public Game(){
		grid = new Grid();
		gesture = new ArrayList<Point>();
		addMouseListener(this);
		addMouseMotionListener(this);
		cells = grid.cellHost();
	}

	@Override
	public void paintComponent(Graphics gr) {
		Graphics2D g = (Graphics2D) gr;
		int w = getWidth(); // gets width and height for resize method to use
		int h = getHeight();

		// Drawing the grid
		g.setPaint(new LinearGradientPaint(new Point(0, 0), new Point(0, w * h),
				new float[] { 0.0f, 1.0f }, new Color[] { BACKGROUND_COLOR, BACKGROUND_COLOR2 },
				MultipleGradientPaint.CycleMethod.NO_CYCLE));

		g.fillRect(0, 0, w, h);
		grid.resize(w, h); // calls resize method to update grid size
		grid.draw(g); // calls draw method from grid

		// Drawing the gesture
		for (int i = 1; i < gesture.size(); i++) {
			int x1 = gesture.get(i - 1).x;
			int y1 = gesture.get(i - 1).y;
			int x2 = gesture.get(i).x;
			int y2 = gesture.get(i).y;
			g.drawLine(x1, y1, x2, y2);
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX(); //gets x coordinate of the mouse release
		int y = e.getY(); //gets y coordinate of the mouse release
		Point dragged = new Point(x,y);
		gesture.add(dragged);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		dx = e.getX(); // gets x coordinate of the mouse press
		dy = e.getY(); // gets y coordinate of the mouse press
		down = grid.whichCell(dx, dy); // finds out the cell where the point belongs
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX(); // gets x coordinate of the mouse release
		int y = e.getY(); // gets y coordinate of the mouse release
		int up = grid.whichCell(x, y); // finds out the cell where the point belongs
		double distance = Point2D.distance(dx, dy, x, y); // calculates distance between two points
		if (down == up) {
			if (up == -1) {
				System.out.println("Out of border!"); // error message if border is clicked
			} else {
				host = cells[up]; // passes current cell to variable host
				if (!host.isOccupied()) {
					if (distance < GESTURE_LENGTH_MULTIPLIER * grid.getCellSize()) {
						same++;
						if (same == 2 || same == -2) {
							showErrorDialog();
						} else {
							host.setToken(new O(host));
						}
						same = 1;
					} else {
						same--;
						if (same == 2 || same == -2) {
							showErrorDialog();
						} else {
							host.setToken(new X(host));
						}
						same = -1;
					}
				}
				grid.snapshot();
			}
		} else {
			System.out.println("Try to draw over one cell at a time."); // error message if user draws over more than one cell at a time
		}

		gesture.clear();
		repaint();

		// calls the checkForWin method from grid class to check if a player has won
		if (grid.checkForWin() != null) {
			int win = JOptionPane.showConfirmDialog(null, grid.checkForWin().toString() + " wins! Play again?",
					"Match Results:", JOptionPane.YES_NO_OPTION);
			if (win == JOptionPane.YES_OPTION) {
				grid.clearTokens(); // clears the cells if the user chooses to play again
				same = 0;
			} else {
				System.exit(0); // exits if the user chooses not to play again
			}
		} else if (grid.isFull()) { // checks if the board is full
			int match = JOptionPane.showConfirmDialog(null, "The board is full. The match is a Tie. Play again?",
					"Match Results:", JOptionPane.YES_NO_OPTION);
			if (match == JOptionPane.YES_OPTION) {
				grid.clearTokens(); // clears the cells if the user chooses to play again
				same = 0;
			} else {
				System.exit(0); // exits if the user chooses not to play again
			}
		} 
		repaint();
	}

	// Method to show an error dialog once a player tries to take a turn twice
	private void showErrorDialog() {
		JOptionPane.showMessageDialog(null, "Player should not take a turn twice!",
				"Invalid turn", JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Tic-Tac-Toe");
		window.setMinimumSize(new Dimension(500, 500));
		window.setSize(800, 800);
		window.setContentPane(new Game());
		window.setVisible(true);
	}

}