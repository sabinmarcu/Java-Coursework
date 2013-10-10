public class Runner{

	public static final String baseURL = "http://www.ecs.soton.ac.uk/people/";
	public static final String baseSearchURL = "https://www.google.com/search?q=";
	public static final String baseAnagramURL = "http://wordsmith.org/anagram/anagram.cgi?t=1000&a=n&anagram=";

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
	}
	private void lookup(){
		if (Model.id == null || Model.id.equals("ECS People")) {
			System.out.println("No person has been found!");
			System.exit(1);
		} else {
			(new PageParser(new Querry(baseURL + Model.id), Model)).parse("ecs");
			(new PageParser(new Querry(baseSearchURL + Model.name.replace(".", "").replace(" ", "+")), Model)).parse("google");
			(new PageParser(new Querry(baseAnagramURL + Model.name.replace(".", "").replace(" ", "+")), Model)).parse("anagram");
		}
	}
	private void print(){
		Printer.print(Model);
	}
}
