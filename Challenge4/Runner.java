import java.util.*;
import java.util.regex.*;

public class Runner {

	private FunnyParser Parser;
	private HashMap<String,Integer> variables;
	private CodeBlock appBlock = new CodeBlock(), currentBlock = appBlock;

	private HashMap<String,Class> TokenMap = new HashMap<String,Class>(){{
		put("{INIT}", InitInstruction.class);
		put("{INPUT}", InputInstruction.class);
		put("{OUTPUT}", OutputInstruction.class);
		put("{COPY}", CopyInstruction.class);
		put("{INCR}", IncrInstruction.class);
		put("{CLEAR}", ClearInstruction.class);
		put("{DECR}", DecrInstruction.class);
	}};

	public Runner(FunnyParser parser) {
		Parser = parser;

		variables = new HashMap<String,Integer>();
		for(String var: parser.variables) variables.put(var, 1337);

		assemble();
	}

	private void assemble() {
		int match; String code, str = new String(Parser.tokenized_string);
		HashMap<String,Integer> Counts = new HashMap<String,Integer>(){{
			put("{WHILE_START}", 0);
			for(Map.Entry<String,Class> set: TokenMap.entrySet())
				put(set.getKey(), 0);
		}};

		while(str.length() > 0) {
			match = str.indexOf("}"); code = str.substring(0, match + 1);
			if (code.equals("{WHILE_START}")) {
				CodeBlock block = new CodeBlock(currentBlock);
				block.decode(Parser.Tokens.get("{WHILE_START}").get(Counts.get("{WHILE_START}")));
				Counts.put("{WHILE_START}", Counts.get("{WHILE_START}") + 1);
				currentBlock.addInstruction(block);
				currentBlock = block;
			}
			if (code.equals("{WHILE_END}")) currentBlock = currentBlock.getParent();
			else for (Map.Entry<String,Class> set: TokenMap.entrySet()) {
				String key = set.getKey();
				if (key.equals(code)) try {
					Instruction instr = (Instruction) TokenMap.get(key).newInstance();
					instr.decode(Parser.Tokens.get(key).get(Counts.get(key)));
					currentBlock.addInstruction(instr);

					Counts.put(key, Counts.get(key) + 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			str = str.substring(match + 1);
		}
	}

	public void run() {
 		appBlock.run(variables);
	}

	public void printVariables() {
		for(Map.Entry<String,Integer> set: variables.entrySet()) System.out.println(set.getKey() + " " + set.getValue());
	}

	public void printResults() {
		String str = "";
		for(Map.Entry<String,Integer> set: variables.entrySet()) str += "; " + set.getKey() + ": " + set.getValue();
		System.out.println(str.substring(2));

	}
}
