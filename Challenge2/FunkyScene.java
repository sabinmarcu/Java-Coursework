public class FunkyScene{

	private static final int OBJECTS_LIMIT = 100;
	private static final float QUICKEN_FACTOR = 0.8f;

	public int width, height, cycles = 1;
	public FunkyBaseObject[] objects = new FunkyBaseObject[100];
	public int objectsNumber = 0;

	private int newObjectThreshold = 1000;

	public void tick() {
		cycles++;
		if (cycles % newObjectThreshold == 0) {
			addObject();
			if ( newObjectThreshold > 500 ) newObjectThreshold = (int) ( (float) newObjectThreshold * QUICKEN_FACTOR );
		}
	}

	public void addObject() {
		if (objectsNumber < OBJECTS_LIMIT)	{
			objects[objectsNumber] = new FunkyBaseObject((int)(Math.random() * width), (int)(Math.random() * height));
			objectsNumber++;
		}
	}
}
