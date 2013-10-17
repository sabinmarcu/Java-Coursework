import java.io.*;
import java.net.*;

public class Query {

	// If the Query refers to a URL page, then a repeat is strongly reccommended
	private boolean repeat = false;
	// I will need a BufferedReader to read from an InputStreamReader attached to a given stream (System.in or an URLConnection stream)
	private BufferedReader br;

	// When constructing the class, I get two cases : System.in or a URL string
	// In this case, the BufferedReader-InputStreamReader set is constructed with System.in
	public Query(InputStream io) {
		br = new BufferedReader(new InputStreamReader(io));
	}

	// In this case, I will construct a URL element, and then a URLConnection to be able to fake the User-Agent (sample taken from the Zepler Labs - Ubuntu PC)
	// Google for example gives a 403 on regular requests, so faking a real browser is a must
	public Query(String url) {
		try {
			repeat = true;
			URL theURL = new URL(url);
			URLConnection theConnection = theURL.openConnection();
			theConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:24.0) Gecko/20100101 Firefox/24.0");
			br = new BufferedReader(new InputStreamReader(theConnection.getInputStream()));
		} catch (Exception exc) {
			System.out.println("An error has occured when trying to open a stream to the URL (" + url + ")");
			System.exit(1);
		}
	}

	// Get the data from the stream.
	public String get() {
		try {
			if (!repeat) return br.readLine();
			else {
				String data = "", inputLine;
				while (( inputLine = br.readLine() ) != null) data = data + inputLine;
				return data;
			}
		} catch (Exception exc) {
			System.out.println("An error has occured when trying to read from the stream");
			System.exit(1);
		}
		return null;
	}
}