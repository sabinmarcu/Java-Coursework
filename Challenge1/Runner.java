import java.net.*;
import java.io.*;

public class Runner{
	// Some constants ...
	public static final String baseURL = "http://www.ecs.soton.ac.uk/people/";
	public static final String baseSearchURL = "https://www.google.com/search?q=";
	public static final String baseAnagramURL = "http://wordsmith.org/anagram/anagram.cgi?t=1000&a=n&anagram=";
	public static final String baseTranslateURL = "http://translate.google.com/#en/fr/";

	// Variables to be used. Results will be held public
	private String id;
	public String name = null;
	public String[] homepage = null;
	public String[] anagrams = null;
	private int anagramsNo = 0;

	public void ask (){
		System.out.print("Enter the ID : ");		// Asking for the id
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			id = br.readLine().trim();
			System.out.println("");
			System.out.println("Thank you. The application will now look for an ECS person, and then search google for the top 3 hits, and then try and find anagrams for that name!");
			System.out.println("");
		} catch (IOException exc) {
			System.out.println("IO error trying to read the ID!");
			System.exit(1);
		}
	}
	public void lookup(){
		lookupEcsPage();
		System.out.println("Done looking up ECS");
		lookupGoogleSearch();
		System.out.println("Done looking up Google");
		lookupAnagram();
		System.out.println("Done Looking up Anagrams");
		lookupTranslate();
		System.out.println("Done Looking up Anagrams");
	}
	private void lookupEcsPage() {
		try {
			// Constructing the URL and Buffers and preparing the local variables
			URL theURL = new URL(Runner.baseURL + id);
			BufferedReader br = new BufferedReader(new InputStreamReader(theURL.openStream()));
			String data = "";
			String inputLine;
			// Reading the URL data
			while (( inputLine = br.readLine() ) != null) data = data + inputLine;
			// And finally parsing the results.
			parseEcsPage(data);
		} catch (Exception exc) {
			System.out.println("Error occurred while looking up the ECS page.");
			System.exit(1);
		}
	}
	private void parseEcsPage(String data){
		int first, last;
		first = data.indexOf("<title>") + 7;
		last = data.indexOf("|", first);
		name = data.substring(first, last).trim();
	}
	private void lookupGoogleSearch() {
		if (name == null || name.equals("ECS People")) System.out.print("Skipping ... ");
		else {
			try{
				// Google does not allow basic URL get requests, so we need to fake it a bit ...
				URL theURL = new URL(Runner.baseSearchURL + name.replace(".", "").replace(" ", "+"));
				URLConnection con = theURL.openConnection();
				// Faking the User Agent string (gotten from the labs in the Zepler Building)
				con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:24.0) Gecko/20100101 Firefox/24.0");
				// Moving forward ...
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String data = "", inputLine;
				while (( inputLine = br.readLine() ) != null) data = data + inputLine;
				parseGoogleSearch(data);
			} catch (Exception exc) {
				System.out.println("Error occurred while looking up the Google Search page.");
				System.out.println(exc);
				System.exit(1);
			}
		}
	}
	private void parseGoogleSearch(String data){
		int first = 0, last, i; homepage = new String[3];
		for (i = 0; i < 3; i++) {
			first = data.indexOf("<cite>", first) + 6;
			last = data.indexOf("</cite>", first);
			homepage[i] = data.substring(first, last).trim().replaceAll("\\<.*?>", "");
			first = last + 7;
		}
	}
	private void lookupAnagram() {
		if (name == null || name.equals("ECS People")) System.out.print("Skipping ... ");
		else {
			String baseURL = Runner.baseAnagramURL + name.replace(".", "").replace(" ", "+");
			try {
				// Constructing the URL and Buffers and preparing the local variables
				URL theURL = new URL(baseURL);
				BufferedReader br = new BufferedReader(new InputStreamReader(theURL.openStream()));
				String data = "";
				String inputLine;
				// Reading the URL data
				while (( inputLine = br.readLine() ) != null) data = data + inputLine;
				// And finally parsing the results.
				parseAnagram(data);
			} catch (Exception exc) {
				System.out.println("Error occurred while looking up the Anagram page. (" + baseURL + ")[" + exc + "]");
				System.exit(1);
			}
		}
	}
	private void parseAnagram(String data){
		int first, last; String inner = "";
		first = data.indexOf("<p><b>") + 6;
		last = data.indexOf("<bottomlinks>", first);
		inner = data.substring(first, last);
		first = inner.indexOf(" ");
		int i;
		try {
			anagramsNo = Integer.parseInt(inner.substring(0, first));
			if (anagramsNo > 5) anagramsNo = 5;
			anagrams = new String[anagramsNo];
			first = inner.indexOf("<br>") + 4;
			for (i = 0; i < anagramsNo; i++) {
				last = inner.indexOf("<br>", first);
				anagrams[i] = inner.substring(first, last);
				first = last + 4;
			}
		} catch (Exception exc) {
		}
	}
	private void lookupTranslate() {
		if (name == null || name.equals("ECS People")) System.out.print("Skipping ... ");
		else {
			String baseURL = Runner.baseTranslateURL + ("My name is " + name.replace(".", "")).replace(" ", "%20");
			try {
				// Constructing the URL and Buffers and preparing the local variables
				URL theURL = new URL(baseURL);
				URLConnection con = theURL.openConnection();
				System.out.println(baseURL);
				// Faking the User Agent string (gotten from the labs in the Zepler Building)
				con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:24.0) Gecko/20100101 Firefox/24.0");
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String data = "";
				String inputLine;
				// Reading the URL data
				while (( inputLine = br.readLine() ) != null) data = data + inputLine;
				// And finally parsing the results.
				parseTranslate(data);
			} catch (Exception exc) {
				System.out.println("Error occurred while looking up the Translate page. (" + baseURL + ")[" + exc + "]");
				System.exit(1);
			}
		}
	}
	private void parseTranslate(String data){
		System.out.println("Parsing Translate");
		System.out.println(data.indexOf("David"));
		System.out.println(data.substring(data.indexOf("Dr")));
	}
	public void print(){
		System.out.println("");
		if (name == null || name.equals("ECS People")) System.out.println("No name has been found!");
		else System.out.println("The name found is : " + name);
		System.out.println("");
		if (homepage == null) System.out.println("Could not find any homepage links on google!");
		else {
			System.out.print("The first three hits on google were : ");
			int i; for (i = 0; i < 3; i++) System.out.print("http://" + homepage[i] + ", ");
			System.out.println("and ... ");
		}
		System.out.println("");
		if (anagrams == null) System.out.println("Could not find any anagrams for this name!");
		else {
			System.out.print("Found the following anagrams for the name given (first 5 at most) : ");
			int i; for (i = 0; i < anagramsNo; i++) System.out.print(anagrams[i] + ", ");
			System.out.println("and ... ");
		}
		System.out.println("");
		System.out.println("That's it for now!");
	}
}
