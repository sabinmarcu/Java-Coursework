import java.util.*;

public class InitInstruction implements Instruction {

	protected String var;
	protected Integer value;

	public InitInstruction() {
	}
	public void decode(String code) {
		String[] args = code.replaceAll(";", "").split(" ");
		if (args[0].equals("init") && args[2].equals("=")){
			var = args[1];
			value = Integer.parseInt(args[3]);
		}
	}
	public void run(HashMap<String,Integer> variables) {
		variables.put(var, value);
	}
}
