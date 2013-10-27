public class Runner{

	// These constants will help in the creation of the urls
	public static final String baseURL = "http://www.ecs.soton.ac.uk/people/<name>";
	public static final String baseSearchURL = "https://www.google.com/search?q=<name>";
	public static final String baseAnagramURL = "http://wordsmith.org/anagram/anagram.cgi?anagram=<name>&t=1000&a=n";
	public static final String baseFeedsURL = "http://www.ecs.soton.ac.uk/people/<name>/publications";

	// And every run of the app will need to have a model to store the data
	private UserModel Model = new UserModel();

	// The basic run sequence 
	public void run() {
		this.ask();
		this.lookup();
		this.print();
	}

	// Ask for a user id and parse it just in case the user had given an email address
	private void ask(){
		System.out.print("Give me an ID : ");
		Query querry = new Query(System.in);
		Model.id = querry.get();
		if (Model.id.indexOf("@soton.ac.uk") > 0) Model.id = Model.id.substring(0, Model.id.indexOf("@soton.ac.uk"));
		if (Model.id.indexOf("@ecs.soton.ac.uk") > 0) Model.id = Model.id.substring(0, Model.id.indexOf("@ecs.soton.ac.uk"));
	}

	// Lookup the web for some information
	private void lookup(){
		// If I don't have any ID (void input), then just print it out and exit.
		if (Model.id == null) {
			System.out.println("No id has been read!");
			System.exit(1);
		} else {
			// Attempt to crawl the ECS page of the inputted user
			(new PageParser(new Query(baseURL.replace("<name>", Model.id)), Model)).parse("ecs");

			// If no name has been found (result is null or 'ECS People', then that means that the ID was invalid. Print and exit.
			if (Model.name == null || Model.name.equals("ECS People")) {
				System.out.println("No name has been found. Shutting down!");
				System.exit(1);
			} else {
				// If a name has been found, that means that I have the user, and I start looking up google, anagrams, and feeds
				(new PageParser(new Query(baseSearchURL.replace("<name>", Model.name.replace(".", "").replace(" ", "+"))), Model)).parse("google");
				(new PageParser(new Query(baseAnagramURL.replace("<name>", Model.name.replace(".", "").replace(" ", "+"))), Model)).parse("anagram");
				(new PageParser(new Query(baseFeedsURL.replace("<name>", Model.id)), Model)).parse("feeds");
			}
		}
	}
	// And now just print the results (if I do have a user)
	private void print(){
		Printer.print(Model);
	}
}
