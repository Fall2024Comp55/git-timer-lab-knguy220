import acm.graphics.*;
import acm.program.*;
import javax.swing.*;
import java.awt.event.*;

public class MyFirstTimer extends GraphicsProgram implements ActionListener {
	public static final int PROGRAM_HEIGHT = 600;
	public static final int PROGRAM_WIDTH = 800;
	public static final int MAX_STEPS = 20;
	private GLabel myLabel;
	private Timer myTimer;
	private int numTimes;

	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
		requestFocus();
	}
	
	public void run() {
		numTimes = 0;
		myLabel = new GLabel("# of times called?", 0, 100);
		add(myLabel);
		
		myTimer = new Timer(1000, this);
        myTimer.setInitialDelay(3000);
		myTimer.start();

	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		numTimes++;
		myLabel.setLabel("times called? " + numTimes);

		
		if (numTimes == 10) {
            myTimer.stop();
            return; 
        }
		
		myLabel.move(5, 0);

    }
	
	public static void main(String[] args) {
		new MyFirstTimer().start();
	}
}