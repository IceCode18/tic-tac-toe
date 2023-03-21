import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	
    private Grid grid;
	private ArrayList<Point> gesture;
	private int down, dx, dy;
	private Cell host;
	private Cell[] cells;
	private int same;

	//constructor
	public Game(){
		grid = new Grid(); //instantiates Grid class
		gesture = new ArrayList<Point>();
		addMouseListener(this);
		addMouseMotionListener(this);
		cells = grid.cellHost();
	}

	@Override
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		int w = getWidth(); // gets width and height for resize method to use
		int h = getHeight();

		g.setColor(Color.WHITE); //sets background color to white
		g.fillRect(0,0,w,h);
		g.setColor(Color.BLACK);
		grid.resize(w, h); // calls resize method to update grid size
		grid.draw(g); //calls grid's draw method to draw grid

		for (int i =1; i<gesture.size(); i++){
			int x1 = gesture.get(i-1).x;
			int y1 = gesture.get(i-1).y;
			int x2 = gesture.get(i).x;
			int y2 = gesture.get(i).y;
			g.drawLine(x1,y1,x2,y2);
		}

	}

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new Game());
		window.setSize(500,500);
        window.setVisible(true);
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
					if (distance < 0.3 * grid.getCellSize()) {
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
			System.out.println("Try to draw over one cell at a time."); // error message if user draws over more than
																		// one cells at a time
		}
		gesture.clear();
		repaint();
	}

	// method to show error dialog if a player tries to take a turn twice
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
	
}