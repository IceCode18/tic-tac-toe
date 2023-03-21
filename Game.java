import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel{
	
    private Grid grid;

	//constructor
	public Game(){
		grid = new Grid(); //instantiates Grid class
	}

	@Override
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		int w = getWidth(); // gets width and height for resize method to use
		int h = getHeight();

		g.setColor(Color.WHITE); //sets background color to white
		g.fillRect(0,0,w,h);

		grid.draw(g); //calls grid's draw method to draw grid

	}

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new Game());
		window.setSize(500,500);
        window.setVisible(true);
	}
	
}