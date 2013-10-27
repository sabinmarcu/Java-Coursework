import java.util.*;

public class ClearInstruction implements Instruction {

	protected String var;

	public ClearInstruction() {
	}
	public void decode(String code) {
		int first = code.indexOf(" "), last = code.indexOf(";");
		var = code.substring(first, last).trim();
	}
	public void run(HashMap<String,Integer> variables) {
		variables.put(var, 0);
	}
}
