import java.util.*;
import java.io.*;

public class CopyInstruction implements Instruction {

	protected String var1, var2;

	public CopyInstruction() {
	}
	public void decode(String code) {
		// copy X, Y;
		String[] args = code.replaceAll("[;,]", "").split(" ");
		var1 = args[1]; var2 = args[2];
	}
	public void run(HashMap<String,Integer> variables) {
		variables.put(var1, variables.get(var2));
	}
}
