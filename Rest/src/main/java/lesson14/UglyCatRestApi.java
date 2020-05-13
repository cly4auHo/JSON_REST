package lesson14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class UglyCatRestApi {
	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String GET_URL = "http://code-master.com.ua/uglycatapi/getflea";
	private static final String DELETE_URL = "http://code-master.com.ua/uglycatapi/deleteflea/";
	private static final String POST_URL = "http://code-master.com.ua/uglycatapi/addflea/";

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
		}
		
		return null;
	}
	
	public Flea getFleaById(String id) {
		try {
			Gson gson = new Gson();
			ResponseFlea response = gson.fromJson(sendRequest("GET", GET_URL, id, null), ResponseFlea.class);
			return response.getUglycat_flea();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Flea> getFleaAll() {	
		try {
			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new StringReader(sendRequest("GET", GET_URL, null, null)));
			reader.setLenient(true);
			ResponceAllFlea response = gson.fromJson(reader, ResponceAllFlea.class);
			return response.getUglycat_flea();	
		} catch (IOException e) {	
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Flea addFlea(Flea flea) {
		try {
			Gson gson = new Gson();
			String jsonFlea = gson.toJson(flea);
			ResponseFlea response = gson.fromJson(sendRequest("POST", POST_URL, null, jsonFlea), ResponseFlea.class);
			return response.getUglycat_flea();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void deleteFleaById(String id) {
		try {
			Gson gson = new Gson();
			ResponseFlea response = gson.fromJson(sendRequest("DELETE", DELETE_URL, id, null), ResponseFlea.class);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
