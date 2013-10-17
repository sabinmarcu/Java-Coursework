import java.util.TimerTask;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class FunkyPainter extends TimerTask {

	private Graphics gfx;
	private FunkyAnimator Animator;
	private FunkyScene Scene;
	private int cycles = 0;

	public FunkyPainter(Graphics gfx, FunkyAnimator animator, FunkyScene scene) {
		this.Animator = animator;
		this.Scene = scene;
		this.gfx = gfx;
		initialDraw();
	}
	private void initialDraw() {
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, Scene.width, Scene.height);
	}

	public void run() {
		cycles++;
		if (cycles % 3 == 0) Animator.tick();
		this.paint();
	}

	private void paint() {
		int i;
		gfx.clearRect(1, 1, Scene.width - 2, Scene.height - 2);
		gfx.setColor(Color.red);
		gfx.setFont(new Font("Arial", 0, 90));
		if (Scene.objectsNumber > 0)
			for (i = 0; i < Scene.objectsNumber; i++)
				if (Scene.objects[i] != null)
					Scene.objects[i].draw(gfx);
	}
}
