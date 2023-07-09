package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static JsonPath parseResponse(String response) {
		
		JsonPath js = new JsonPath(response);
		return js;
	}

}
