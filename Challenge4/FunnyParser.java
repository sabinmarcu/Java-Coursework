import java.util.*;
import java.util.regex.*;

public class FunnyParser {

	private String raw_string;

	public String tokenized_string;
	public ArrayList<String> variables = new ArrayList<String>();

	private static HashMap<String,String> TOKENS = new HashMap<String,String>(){{
		put("{CLEAR}", "clear\\s+([a-zA-Z]+);");
		put("{INCR}", "incr\\s+([a-zA-Z]+);");
		put("{DECR}", "decr\\s+([a-zA-Z]+);");
		put("{WHILE_START}", "(while)\\s");
		put("{EXPRESSION}", "[^(while);{}]+not[^;{}]+;");
		put("{WHILE_END}", "end;");
	}};

	public HashMap<String,HashMap<Integer,String>> Tokens = new HashMap<String,HashMap<Integer,String>>(){{
		for(Map.Entry<String,String> set: TOKENS.entrySet()) {
			put(set.getKey(), new HashMap<Integer,String>());
		}
	}};

	public FunnyParser(String raw) {
		raw_string = raw;
		getVariables();
		tokenize();
	}

	private void getVariables() {
		Pattern pat = Pattern.compile("(clear|incr|decr)\\s([a-zA-Z]+);", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE), nextpat = Pattern.compile("\\s([a-zA-Z]+)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pat.matcher(raw_string), nextmat;
		String match;
		while(matcher.find()) {
			nextmat = nextpat.matcher(matcher.group());
			while(nextmat.find())
				if (!variables.contains(nextmat.group().trim()))
					variables.add(nextmat.group().trim());
		}
	}

	private void tokenize() {
		tokenized_string = new String(raw_string); Pattern pat; Matcher mat; int it;
		for (Map.Entry<String,String> set: TOKENS.entrySet()) {
			pat = Pattern.compile(set.getValue()); mat = pat.matcher(tokenized_string); it = 0;
			while (mat.find()) {
				Tokens.get(set.getKey()).put(it, mat.group());
				it += 1;
			}
			tokenized_string = tokenized_string.replaceAll(set.getValue(), set.getKey());
		}
		tokenized_string = tokenized_string.replaceAll("\\s", "");
	}

}
