import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;

public class ColorCircleDecomp extends GraphicsProgram implements ActionListener {
	public static final int PROGRAM_WIDTH = 800;
	public static final int PROGRAM_HEIGHT = 600;
	public static final int BALL_SIZE = 50;
	public static final int DELAY_MS = 25;
	public static final int RADIUS = 200;

	private GOval ball;
	private double angle = 0;
	private int colorCycle = 0; // Keeps track of the color change cycle

	public void run() {
		// TODO add your ball here
		ball = new GOval(PROGRAM_WIDTH / 2 - BALL_SIZE / 2, PROGRAM_HEIGHT / 2 + RADIUS - BALL_SIZE / 2, BALL_SIZE, BALL_SIZE);
        ball.setFilled(true);
        ball.setColor(new Color(0, 85, 170)); 
        add(ball);
        
        // Start the timer to handle movement and color change
        Timer t = new Timer(DELAY_MS, this);
        t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		double centerX = PROGRAM_WIDTH / 2;
        double centerY = PROGRAM_HEIGHT / 2;
        
        double x = centerX + RADIUS * Math.cos(Math.toRadians(angle)) - BALL_SIZE / 2;
        double y = centerY + RADIUS * Math.sin(Math.toRadians(angle)) - BALL_SIZE / 2;
        
        ball.setLocation(x, y);
        
        angle += 5; 
        angle %= 360; // Loop back to 0 after a full circle
        
        // Calculate RGB values in a zigzag pattern within the 0-255 range
        int redValue = 128 + (int)(127 * Math.sin(Math.toRadians(colorCycle)));
        int greenValue = 128 + (int)(127 * Math.sin(Math.toRadians(colorCycle + 120)));
        int blueValue = 128 + (int)(127 * Math.sin(Math.toRadians(colorCycle + 240)));  
        
        ball.setColor(new Color(redValue, greenValue, blueValue));
        
        // Print the RGB values to see the changes
        System.out.println("red: " + redValue + " green: " + greenValue + " blue: " + blueValue);
        
        // Increment colorCycle to keep colors changing
        colorCycle = (colorCycle + 2) % 360;

	}

	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}

	public static void main(String args[]) {
		new ColorCircleDecomp().start();
	}

}