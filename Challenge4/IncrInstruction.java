import java.util.*;

public class IncrInstruction implements Instruction {

	protected String var;

	public IncrInstruction() {
	}
	public void decode(String code) {
		int first = code.indexOf(" "), last = code.indexOf(";");
		var = code.substring(first, last).trim();
	}
	public void run(HashMap<String,Integer> variables) {
		variables.put(var, variables.get(var) + 1);
	}
}
