public class FunkyCoordinate {
	public int x, y;
	public FunkyCoordinate(int x, int y) {
		this.move(x, y);
	}
	public void move(float x, float y) {
		this.x = (int) x; this.y = (int) y;
	}
}