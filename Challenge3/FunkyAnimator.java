import java.util.*;

public class FunkyAnimator {

	private FunkyScene Scene;
	private double cycle = 0;
	private HashMap<String,Integer> Settings;

	public FunkyAnimator(FunkyScene scene, HashMap<String,Integer> settings) {
		this.Settings = settings;
		this.Scene = scene;
	}

	public void tick() {
		double cycle = Math.toRadians(this.cycle);
		Scene.spiro.position.move(
			//(float)( (Settings.get("R") + Settings.get("r")) * Math.cos(cycle) + Settings.get("offset") * Math.cos(( Settings.get("R") + Settings.get("r") ) / Settings.get("r") * cycle) ),
			//(float)( (Settings.get("R") + Settings.get("r")) * Math.sin(cycle) + Settings.get("offset") * Math.sin(( Settings.get("R") + Settings.get("r") ) / Settings.get("r") * cycle) )

			//x = (R+r)*cos(t) - (r+O)*cos(((R+r)/r)*t)
			//y = (R+r)*sin(t) - (r+O)*sin(((R+r)/r)*t)
			(float)( (Settings.get("R") + Settings.get("r")) * Math.cos(cycle) - ( Settings.get("r") + Settings.get("offset") ) * Math.cos(( Settings.get("R") + Settings.get("r") ) / Settings.get("r") * cycle) ),
			(float)( (Settings.get("R") + Settings.get("r")) * Math.sin(cycle) - ( Settings.get("r") + Settings.get("offset") ) * Math.sin(( Settings.get("R") + Settings.get("r") ) / Settings.get("r") * cycle) )
		);
		this.cycle = this.cycle + 0.5;
		if (this.cycle > 360) this.cycle = 0;
	}
}
