import java.awt.Graphics;
import java.awt.Color;

public class FunkyBaseObject {

	public FunkyCoordinate position;
	private Color color;

	public FunkyBaseObject() {
		color = Color.black;
		position = new FunkyCoordinate(0, 0);
	}
	public void draw(Graphics gfx) {
		gfx.setColor(color);
		gfx.fillRect(position.x, position.y, 1, 1);
	}
}
