import java.util.*;
import java.io.*;

public class InputInstruction implements Instruction {

	protected String var;

	public InputInstruction() {
	}
	public void decode(String code) {
		int first = code.indexOf(" "), last = code.indexOf(";");
		var = code.substring(first, last).trim();
	}
	public void run(HashMap<String,Integer> variables) {
		Integer value = null;
		while (value == null) {
			System.out.print("Enter a number for " + var + " : ");
			try	{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				value = Integer.parseInt(br.readLine());
			} catch (Exception e) {
				System.out.println("Error. Please try again!");
			}
		}
		variables.put(var, value);
	}
}
