package files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {
	
	public static JsonPath RawToJSON(String response) {
		JsonPath js1 = new JsonPath(response);
		return js1;

		
		
	}

}
