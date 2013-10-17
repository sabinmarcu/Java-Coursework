import java.util.Timer;
import java.util.TimerTask;
import java.applet.Applet;
import java.awt.Graphics;

@SuppressWarnings("serial") public class FunkyApplet extends Applet {
	private static final int FRAME_TIME = 2;
	private Timer timer;
	private FunkyScene Scene;
	private FunkyPainter Painter;
	private FunkyAnimator Animator;

	public void start() {
		final Graphics graphics = getGraphics();
		Scene = new FunkyScene();
		Animator = new FunkyAnimator(Scene);
		Painter = new FunkyPainter(graphics, Animator, Scene);
		timer = new Timer(true);
		setup();
		timer.scheduleAtFixedRate(Painter, 0, FRAME_TIME);
	}

	public void stop() {
		timer.cancel();
	}

	public void setup() {
		Scene.width = getWidth();
		Scene.height = getHeight();
		Scene.addObject();
	}

}
