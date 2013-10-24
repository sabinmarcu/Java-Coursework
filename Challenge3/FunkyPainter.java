import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.event.*;
import java.util.*;

public class FunkyPainter {

	private Graphics2D graphics;
	private Graphics gfx;
	private FunkyScene Scene;
	private FunkyApplet Applet;
	private Image offscreenImage;
	private HashMap<String,Integer> Settings;
	public FunkyCoordinate lastCoords;

	public FunkyPainter(Graphics gfx, FunkyScene scene, Image img, HashMap<String,Integer> settings, FunkyApplet applet) {
		this.Scene = scene;
		this.graphics = (Graphics2D) gfx;
		this.offscreenImage = img;
		this.gfx = this.offscreenImage.getGraphics();
		this.Settings = settings;
		this.Applet = applet;
		initialDraw();
	}
	private void initialDraw() {
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, Scene.width, Scene.height);
	}

	public void resetCanvas() {
		gfx.clearRect(0, 0, Scene.height, Scene.height);
	}

	public void paintImage() {
		graphics.drawImage(offscreenImage, 0, 0, Applet);
	}

	private void paintObjects(){
		System.out.println("Drawing " + Scene.spiro.position.x + ", " + Scene.spiro.position.y);
		int currentX = Scene.width / 2 + Scene.spiro.position.x, currentY =Scene.height / 2 + Scene.spiro.position.y;

		if (lastCoords == null) gfx.fillRect(currentX, currentY, 2, 2);
		else gfx.drawLine(lastCoords.x, lastCoords.y, currentX, currentY);

		lastCoords = new FunkyCoordinate(currentX, currentY);
	}

	public void paint() {
		paintObjects(); paintImage();
	}
}
