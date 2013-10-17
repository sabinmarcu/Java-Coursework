public class FunkyForce {

	public float xPerTick, yPerTick;
	private FunkyBaseObject object;

	public FunkyForce(FunkyBaseObject obj) {
		xPerTick = ((float) (Math.random() * 3)) ;
		yPerTick = ((float) (Math.random() * 3)) ;
		object = obj;
	}
	public void apply() {
		object.coords.move(
			object.coords.x + xPerTick,
			object.coords.y + yPerTick
		);
	}
	public void bounceX() { xPerTick = -xPerTick; }
	public void bounceY() { yPerTick = -yPerTick; }
}
