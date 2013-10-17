// A page reader class to be used in coordonation with a Query and a Model, that will be updated while parsing the data returned by the Query
public class PageParser{

	// The data returned from the Query, and a local Model refference
	private String data;
	private UserModel Model;

	// When constructing the parser, I update the data, and refference the model
	public PageParser(Query q, UserModel m) {
		data = q.get(); Model = m;
	}

	// When calling parse, I need to handle more than one case (more than one type of page)
	public void parse(String what){
		switch (what) {
			case "ecs": this.parseECS(); break;
			case "google": this.parseGoogle(); break;
			case "anagram": this.parseAnagram(); break;
			case "feeds": this.parseFeeds(); break;
		}
	}

	// When parsing the ECS page, I will take the name from the title, and then role, telephone number, email, homepage, and the other stuff from the body of the page
	private void parseECS() {
		int first, last, third;
		// Getting the name
		first = data.indexOf("title>") + 6;
		last = data.indexOf("|", first);
		if (first > 100 && last > 0 && first < last) Model.name = sanitize(data.substring(first, last));
		// Getting the role
		first = data.indexOf("'role'>") + 7;
		last = data.indexOf("<br/>", first);
		if (first > 100 && last > 0 && first < last) Model.role = sanitize(data.substring(first, last));
		// Getting the phone number
		first = data.indexOf("\"tel\">") + 6;
		first = data.indexOf("\"value\">", first) + 8;
		last = data.indexOf("<br/>", first);
		if (first > 100 && last > 0 && first < last) Model.phone = sanitize(data.substring(first, last));
		// Getting the email address
		first = data.indexOf("\"email\">") + 8;
		last = data.indexOf("<br/>", first);
		if (first > 100 && last > 0 && first < last) Model.email = sanitize(data.substring(first, last));
		// Getting the homepage
		first = data.indexOf("Homepage:") + 18;
		last = data.indexOf("<br/>", first);
		if (first > 100 && last > 0 && first < last) Model.homepage = sanitize(data.substring(first, last));
		// Getting the interests
		first = data.indexOf("Interests:") + 19;
		last = data.indexOf("<br/>", first);
		if (first > 100 && last > 0 && first < last) Model.interests = sanitize(data.substring(first, last));
		// Getting the people
		int init = data.indexOf("<td><a", first); first = 0; String temp;
		do {
			first = data.indexOf("<td><a", first) + 19;
			last = data.indexOf("</td>", first);
			if (first > 0 && last > 0 && first < last) {
				if (first < 100) break;
				temp = sanitize(data.substring(first, last));
				third = temp.indexOf(">");
				temp = temp.substring(third + 1) + " [http:/" + temp.substring(0, third - 1) + "]";
				Model.people.add(temp);
			}
			first = last;
		} while (first > init);
	}

	// WHen parsing the Google results, I will just look for the *cite* elements beneath the actual links to get the urls
	private void parseGoogle() {
		int first = 0, last;
		int init = data.indexOf("<cite>"); String temp;
		do {
			first = data.indexOf("<cite>", first) + 6;
			last = data.indexOf("</cite>", first);
			if (first > 0 && last > 0 && first < last) {
				temp = sanitize(data.substring(first, last));
				if (first < 100) break;
				Model.searches.add(temp);
			}
			first = last;
		} while (first > init);
	}

	// Parsing the anagrams is a bit tricky, because the site is badly made.
	private void parseAnagram() {
		int first, last;
		first = data.indexOf("<p><b>") + 6;
		last = data.indexOf("<bottomlinks>", first);
		data = data.substring(first, last); int init = data.indexOf("<br>"); String temp; first = 0;
		do {
			first = data.indexOf("<br>", first) + 4;
			last = data.indexOf("<br>", first);
			if (first > 0 && last > 0 && first < last) {
				temp = sanitize(data.substring(first, last));
				Model.anagrams.add(temp);
			}
			first = last;
		} while (first > init);
	}

	// Parsing the feeds is easier than any of the above, since all of the feeds are contained in a span with a class of 'ep_search_feed'
	private void parseFeeds(){
		int first = 0, last, n = 3, i; String temp;
		for (i = 0; i < n; i++) {
			first = data.indexOf("ep_search_feed\"", first) + 18;
			last = data.indexOf("</span>", first);
			temp = sanitize(data.substring(first, last));
			temp = temp.substring(6); temp = temp.substring(0, temp.indexOf("\""));
			Model.feeds.add(temp.trim());
		}
	}

	// For any data extracted, it's useful to replace all tag-like structures with nothing, and trim the strings
	private String sanitize(String data) {
		return data.trim().replaceAll("\\<.*?>", "").trim();
	}
}
