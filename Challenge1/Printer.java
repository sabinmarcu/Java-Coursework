public class Printer{
	static void print(UserModel m) {
		System.out.print("\n\n\n\n");
		System.out.println("************************************************************************************************************");
		System.out.println("ID : " + m.id);
		if (m.name != null) System.out.println("Name : " + m.name);
		if (m.role != null) System.out.println("Role : " + m.role);
		if (m.phone != null) System.out.println("Phone Number : " + m.phone);
		if (m.email != null) System.out.println("Email Address : " + m.email);
		if (m.homepage != null) System.out.println("Homepage : " + m.homepage);
		if (m.interests != null) System.out.println("Interests : " + m.interests);
		if (m.people.size() > 0) {
			System.out.println("************************************************************************************************************");
			System.out.println("Connections :\n");
			int i, n = m.people.size(); for (i = 0; i < n; i++) {
				System.out.println("    " + m.people.get(i));
			}
		}
		if (m.searches.size() > 0) {
			String temp;
			System.out.println("************************************************************************************************************");
			System.out.println("Google Search Results (first page) :\n");
			int i, n = m.searches.size(); for (i = 0; i < n; i++) {
				temp = m.searches.get(i); if (!temp.substring(0, 4).equals("http")) temp = "http://" + temp;
				System.out.println("    " + temp);
			}
		}
		if (m.anagrams.size() > 0) {
			String temp;
			System.out.println("************************************************************************************************************");
			System.out.print("Anagrams :");
			int i, n = m.anagrams.size(); 
			if (n > 200) {
				n = 200;
				System.out.print(" (first 200)");
			}
			System.out.println("\n");
			System.out.print("    " + m.anagrams.get(0));
			for (i = 1; i < n; i++) {
				System.out.print(", " + m.anagrams.get(i));
			}
			System.out.print("\n");
		}
		System.out.println("************************************************************************************************************\n");
	}
}