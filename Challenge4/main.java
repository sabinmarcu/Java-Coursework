public class main {
	public static void main(String[] args) {
		String file = "./file"; if (args[0] != null) file = args[0];
		String contents = Util.readStringFromFile(file);
		FunnyParser parser = new FunnyParser(contents);
		System.out.println(parser.tokenized_string);
		System.out.println(parser.variables);
		System.out.println(parser.Tokens);

	}

}
