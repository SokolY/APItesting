package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethod {
	public static JsonPath rawToJson(String input) {
		JsonPath jsRespGetPlace = new JsonPath(input);
		return jsRespGetPlace;
	}

}
