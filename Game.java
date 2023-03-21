import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel{
	
    private Grid grid;

	//constructor
	public Game(){
		grid = new Grid(); //instantiates Grid class
	}

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(900,800);
		window.setContentPane(new Game());
        window.setVisible(true);
	}
	
}