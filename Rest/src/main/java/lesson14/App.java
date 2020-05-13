package lesson14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class App {
	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_URL = "http://code-master.com.ua/uglycatapi/getflea/?id=63";

	private static final String GET_ALL_URL = "http://code-master.com.ua/uglycatapi/getflea";

	private static final String POST_URL = "http://code-master.com.ua/uglycatapi/addflea/";

	private static final String POST_PARAMS = "userName=Pankaj";

	public static void main(String[] args) throws IOException {

//    		sendGET();
//    		System.out.println("GET DONE");
//    		sendPOST();
//    		System.out.println("POST DONE");

//		System.out.println(createFlea(getFlea()));
		sendPOST("{\r\n" + "            \"id\": \"9\",\r\n" + "            \"name\": \"mmm\",\r\n"
				+ "            \"color\": \"black\"\r\n" + "        }");
	}

	private static ResponseFlea createFlea(String jsonFlea) {
		Gson gson = new Gson();
		ResponseFlea flea = gson.fromJson(jsonFlea, ResponseFlea.class);
		return flea;
	}

	private static String sendRequest(String requestMethod, String url, String id, String jsonInputString)
			throws IOException {
		
		if (id != null) {
			url += "/?id=" + id;
		}
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod(requestMethod);
		con.setRequestProperty("User-Agent", USER_AGENT);
		
		if (jsonInputString != null) {
			con.setDoOutput(true);

			try (OutputStream os = con.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);
				os.flush();
				os.close();
			}
		}
		
		int responseCode = con.getResponseCode();
		System.out.println("Response Code :: " + responseCode);
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();

			// print result
			System.out.println(response.toString());
			return response.toString();
		} else {
			System.out.println("GET request not worked");
			return null;
		}
	}

	private static String getFlea(String url, String id) throws IOException {
		if (id != null) {
			url += "/?id=" + id;
		}
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();

			// print result
			System.out.println(response.toString());
			return response.toString();
		} else {
			System.out.println("GET request not worked");
			return null;
		}
	}

	private static void sendPOST(String jsonInputString) throws IOException {
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		// For POST only - START
		con.setDoOutput(true);

		try (OutputStream os = con.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
			os.flush();
			os.close();
		}

		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("POST request not worked");
		}
	}
}
