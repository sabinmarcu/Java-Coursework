import java.awt.Graphics;
import java.awt.Color;

public class FunkyBaseObject {

	public static final Color[] COLORS = {Color.red, Color.green, Color.blue, Color.black, Color.yellow, Color.gray, Color.cyan, Color.lightGray, Color.magenta, Color.orange, Color.pink};
	public static final Integer[] SIZES = {10, 10};

	public FunkyCoordinate coords;
	public FunkyForce force;
	private Color color;

	public FunkyBaseObject(int x, int y) {
		color = COLORS[((int) (Math.random() * COLORS.length))];
		coords = new FunkyCoordinate(x, y);
		force = new FunkyForce(this);
	}

	public void move() {
		this.force.apply();
	}

	public void draw(Graphics gfx) {
		gfx.setColor(color);
		gfx.fillRect(coords.x - SIZES[0] / 2, coords.y - SIZES[1] / 2, SIZES[0], SIZES[1]);
	}
}
