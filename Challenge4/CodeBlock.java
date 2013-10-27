import java.util.*;

public class CodeBlock implements Instruction {

	private CodeBlock Parent = null;

	private String var = null, comparator = null;
	private Integer comparatee = 0;

	private ArrayList<Instruction> Instructions = new ArrayList<Instruction>();

	public CodeBlock() {}
	public CodeBlock(CodeBlock p) { Parent = p; }

	public void decode(String code) {
		String[] args = code.trim().split(" ");
		var = args[1]; comparator = args[2]; comparatee = Integer.parseInt(args[3]);
	}
	public void run(HashMap<String,Integer> variables) {
		if (var == null) run_tick(variables);
		else {
			if (comparator.equals("is")) while(variables.get(var) == comparatee) run_tick(variables);
			else if (comparator.equals("not")) while(variables.get(var) != comparatee) run_tick(variables);
		}
	}

	private void run_tick(HashMap<String,Integer> variables) {
		for(Instruction instr: Instructions) instr.run(variables);
	}

	public void addInstruction(Instruction instr) {
		Instructions.add(instr);
	}

	public CodeBlock getParent() { return Parent; }

}