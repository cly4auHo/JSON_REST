package lesson14;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

public class FleaMain {

	public static void main(String[] args) {
		String line = "{\"id\":\"63\",\"name\":\"bee\",\"color\":\"yellow\"}";
		
		JSONObject json = new JSONObject(line);
		System.out.println(json.getString("id"));
		OutputStream out = new ByteArrayOutputStream();
		JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(out));
		
		try {
			jsonWriter.beginObject();

			jsonWriter.name("id");
			jsonWriter.jsonValue("63");
			
			jsonWriter.name("name");
			jsonWriter.jsonValue("bee");
			
			jsonWriter.name("color");
			jsonWriter.jsonValue("red");
			

			jsonWriter.endObject();
			jsonWriter.close();
			System.out.println(out.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		Flea flea = gson.fromJson(line, Flea.class);
		
		System.out.println(flea);
	}
}
