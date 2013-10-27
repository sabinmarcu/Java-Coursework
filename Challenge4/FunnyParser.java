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
		put("{INIT}", "init\\s+([a-zA-Z]+)\\s+\\=\\s+([0-9]);");
		put("{INPUT}", "input\\s+([a-zA-Z]+);");
		put("{OUTPUT}", "output\\s+([a-zA-Z]+);");
		put("{COPY}", "copy\\s+([a-zA-Z]+)\\s*\\,\\s+([a-zA-Z]+);");
		put("{WHILE_START}", "while[^;{}]+not[^;{}]+;");
		put("{WHILE_END}", "end;");
	}};

	public HashMap<String,ArrayList<String>> Tokens = new HashMap<String,ArrayList<String>>(){{
		for(Map.Entry<String,String> set: TOKENS.entrySet()) {
			put(set.getKey(), new ArrayList<String>());
		}
	}};

	public FunnyParser(String raw) {
		raw_string = raw.toLowerCase();
		getVariables();
		tokenize();
	}

	private void getVariables() {
		Pattern pat = Pattern.compile("(clear|incr|decr|init|input)\\s([a-zA-Z]+)[\\s\\=0-9]*;", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE), nextpat = Pattern.compile("\\s([a-zA-Z]+)", Pattern.CASE_INSENSITIVE);
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
		tokenized_string = new String(raw_string); Pattern pat; Matcher mat;
		for (Map.Entry<String,String> set: TOKENS.entrySet()) {
			pat = Pattern.compile(set.getValue()); mat = pat.matcher(tokenized_string);
			while (mat.find()) {
				Tokens.get(set.getKey()).add(mat.group());
			}
			tokenized_string = tokenized_string.replaceAll(set.getValue(), set.getKey());
		}
		tokenized_string = tokenized_string.replaceAll("\\s", "");
	}

}
