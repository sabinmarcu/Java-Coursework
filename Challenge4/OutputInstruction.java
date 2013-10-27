import java.util.*;
import java.io.*;

public class OutputInstruction implements Instruction {

	protected String var;

	public OutputInstruction() {
	}
	public void decode(String code) {
		int first = code.indexOf(" "), last = code.indexOf(";");
		var = code.substring(first, last).trim();
	}
	public void run(HashMap<String,Integer> variables) {
		System.out.println(var + " is " + variables.get(var));
	}
}
