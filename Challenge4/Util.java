import java.io.*;

public class /*fucking*/ Util {
	public static String readStringFromFile(String file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = "", line;
			while ((line = br.readLine()) != null) s = s + line;
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
