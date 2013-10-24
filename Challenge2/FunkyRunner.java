import java.util.*;
import java.lang.Runnable;

public class FunkyRunner implements Runnable{
	private FunkyPainter Painter;
	private FunkyAnimator Animator;
	private HashMap<String,Integer> Settings;

	public FunkyRunner(FunkyPainter painter, FunkyAnimator animator, HashMap<String,Integer> settings) {
		Animator = animator;
		Painter = painter;
		Settings = settings;
	}
	public void run() {
		if (Settings.get("shouldDraw") == 0) return;
		Animator.tick();
		Painter.paint();
	}
}
