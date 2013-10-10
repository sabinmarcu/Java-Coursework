public class PageParser{
	private String data;
	private UserModel Model;
	public PageParser(Querry q, UserModel m) {
		data = q.get(); Model = m;
	}
	public void parse(String what){
		switch (what) {
			case "ecs": this.parseECS(); break;
			case "google": this.parseGoogle(); break;
			case "anagram": this.parseAnagram(); break;
		}
	}
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
				temp = sanitize(data.substring(first, last)); 
				if (first < 100) break;
				third = temp.indexOf(">");
				temp = temp.substring(third + 1) + " [http:/" + temp.substring(0, third - 1) + "]";
				Model.people.add(temp);
			}
			first = last;
		} while (first > init);
	}

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

	private String sanitize(String data) {
		return data.trim().replaceAll("\\<.*?>", "").trim();
	}
}