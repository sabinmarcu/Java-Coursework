public class FunkyForce {

	public float xPerTick = 0, yPerTick = 0;
	private FunkyBaseObject object;

	public FunkyForce(FunkyBaseObject obj) {
		while (xPerTick <= 0.1 && yPerTick <= 0.1){
			xPerTick = (0.1 + (float) (Math.random() * 4));
			yPerTick = (0.1 + (float) (Math.random() * 4));
		}
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
