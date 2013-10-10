import java.io.*;
import java.net.*;

public class Query {
	private boolean repeat = false;
	private BufferedReader br;
	public Query(InputStream io) {
		br = new BufferedReader(new InputStreamReader(io));
	}
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