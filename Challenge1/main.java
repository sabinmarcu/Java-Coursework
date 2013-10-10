public class main{ 
	public static void main(String[] args){ 

		// Just in case ...
		System.getProperties().put("proxySet", "true");
		System.getProperties().put("proxyHost", "152.78.128.51");
		System.getProperties().put("proxyPort", "3128");

		// Start the app ...
		(new Runner()).run(); 
	} 
}
