import acm.graphics.*;
import acm.program.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;

public class BallLauncher extends GraphicsProgram implements ActionListener {

	public static final int SIZE = 25;
	public static final int MS = 50;
	public static final int SPEED = 2;

	public static final int PROGRAM_HEIGHT = 600;
	public static final int PROGRAM_WIDTH = 800;

	private ArrayList<GOval> balls;
	private Timer moveTimer;

	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
		requestFocus();
	}

	public void run() {
		balls = new ArrayList<>();
		addMouseListeners();
		moveTimer = new Timer(MS, this);
		moveTimer.start();
	}

	public void mousePressed(MouseEvent e) {
		for (GOval ball : balls) {
			if (ball.getX() < 100) {

				GOval cooldownIndicator = new GOval(e.getX() - SIZE / 2, e.getY() - SIZE / 2, SIZE, SIZE);
				cooldownIndicator.setColor(Color.YELLOW);
				cooldownIndicator.setFilled(true);
				add(cooldownIndicator);

				new Timer(200, evt -> remove(cooldownIndicator)).start();

				return;
			}
		}

		GOval ball = makeBall(SIZE / 2, e.getY());
		add(ball);
		balls.add(ball);
	}

	public GOval makeBall(double x, double y) {
		GOval temp = new GOval(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE);
		temp.setColor(Color.RED);
		temp.setFilled(true);
		return temp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (GOval ball : balls) {
			ball.move(SPEED, 0);
		}
	}

	public static void main(String[] args) {
		new BallLauncher().start();
	}

}
