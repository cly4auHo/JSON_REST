package lesson14;

import com.google.gson.Gson;

public class UglyCatRestApiMain {

	public static void main(String[] args) {
		UglyCatRestApi uglyCatRestApi = new UglyCatRestApi();
//		System.out.println(uglyCatRestApi.getFleaById("25"));
//		System.out.println(uglyCatRestApi.getFleaAll());
		Flea flea = new Flea(210, "miniMax", "green");
		System.out.println(uglyCatRestApi.addFlea(flea));
		
	}
}
