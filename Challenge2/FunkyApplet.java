import java.util.Timer;
import java.util.TimerTask;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial") public class FunkyApplet extends Applet {
	public ReschedulableTimer timer;
	private FunkyScene Scene;
	private FunkyPainter Painter;
	private FunkyAnimator Animator;
	private Canvas canvas;
	private FunkyPanel menu;
	private FunkyHandler Handler;

	public void init() {
		setLayout(new GridBagLayout());
		canvas = new Canvas();
		menu = new FunkyPanel(new GridBagLayout(), this);
		GridBagConstraints c=  new GridBagConstraints();

		canvas.setSize((int)( getWidth() * 0.8 ), getHeight());
		menu.setSize((int)( getWidth() * 0.2 ), getHeight());

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.8;
		c.anchor = GridBagConstraints.NORTHWEST;
		add(canvas, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.2;
		c.anchor = GridBagConstraints.NORTHEAST;
		add(menu, c);
	}

	public void start() {
		timer = new ReschedulableTimer();
		Image img = createImage(getHeight(), getHeight());
		Scene = new FunkyScene();
		Animator = new FunkyAnimator(Scene);
		Painter = new FunkyPainter(canvas.getGraphics(), Scene, img, menu.settings, this);
		Handler = new FunkyHandler(Scene);
		canvas.addMouseListener(Handler);
		timer.schedule(new FunkyRunner(Painter, Animator, menu.settings), (long)(1000 / menu.settings.get("speed") ));
		setup();
	}

	public void startOver() {
		timer.reschedule((long)(1000 / menu.settings.get("speed")));
	}

	public void addObject() {
		Scene.addObject();
	}

	public void resetObjects(){
		Scene.objects = new FunkyBaseObject[FunkyScene.OBJECTS_LIMIT];
		Scene.objectsNumber = 0;
		Painter.resetCanvas(); Painter.paintImage();
	}

	public void stop() {
		timer.cancel();
		canvas.removeMouseListener(Handler);
	}

	public void setup() {
		Scene.width = getWidth();
		Scene.height = getHeight();
		Scene.addObject();
	}

}
