public class FunkyForce {

	public int xPerTick = 0, yPerTick = 0;
	private FunkyBaseObject object;

	public FunkyForce(FunkyBaseObject obj) {
		int dirX, dirY;

		while (xPerTick <= 0.1 || yPerTick <= 0.1){
			xPerTick = (int)( Math.random() * 4 );
			yPerTick = (int)( Math.random() * 4 );
		}

		System.out.println(xPerTick + ", " + yPerTick);

		dirX = (int) (Math.random() * 10);
		dirY = dirX % 2; dirX = dirX / 2;

		if (dirX > 0) xPerTick = -xPerTick;
		if (dirY > 0) yPerTick = -yPerTick;

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
