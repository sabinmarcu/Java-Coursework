public class Runner{

	public static final String baseURL = "http://www.ecs.soton.ac.uk/people/<name>";
	public static final String baseSearchURL = "https://www.google.com/search?q=<name>";
	public static final String baseAnagramURL = "http://wordsmith.org/anagram/anagram.cgi?anagram=<name>&t=1000&a=n";
	public static final String baseFeedsURL = "http://www.ecs.soton.ac.uk/people/<name>/publications";

	private UserModel Model = new UserModel();

	public void run() {
		this.ask();
		this.lookup();
		this.print();
	}
	private void ask(){
		System.out.print("Give me an ID : ");
		Querry querry = new Querry(System.in);
		Model.id = querry.get();
		if (Model.id.indexOf("@soton.ac.uk") > 0) Model.id = Model.id.substring(0, Model.id.indexOf("@soton.ac.uk"));
		if (Model.id.indexOf("@ecs.soton.ac.uk") > 0) Model.id = Model.id.substring(0, Model.id.indexOf("@ecs.soton.ac.uk"));
	}
	private void lookup(){
		if (Model.id == null) {
			System.out.println("No id has been read!");
			System.exit(1);
		} else {
			(new PageParser(new Querry(baseURL.replace("<name>", Model.id)), Model)).parse("ecs");
			if (Model.name == null || Model.name.equals("ECS People")) {
				System.out.println("No name has been found. Shutting down!");
				System.exit(1);
			} else {
				(new PageParser(new Querry(baseSearchURL.replace("<name>", Model.name.replace(".", "").replace(" ", "+"))), Model)).parse("google");
				(new PageParser(new Querry(baseAnagramURL.replace("<name>", Model.name.replace(".", "").replace(" ", "+"))), Model)).parse("anagram");
				(new PageParser(new Querry(baseFeedsURL.replace("<name>", Model.id)), Model)).parse("feeds");
			}
		}
	}
	private void print(){
		Printer.print(Model);
	}
}
