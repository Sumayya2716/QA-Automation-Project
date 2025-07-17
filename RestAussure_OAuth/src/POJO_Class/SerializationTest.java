package POJO_Class;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SerializationTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Serailization_AddPlace a = new Serailization_AddPlace();
		a.setAccuracy(50);
		a.setName("Frontline house");
		a.setPhone_number("(+91) 983 893 3937");
		a.setAddress("29, side layout, cohen 09");
		a.setWebsite("http://google.com");
		a.setLanguage("French-IN");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		a.setTypes(myList);

		Serailization_SubLocation L= new Serailization_SubLocation();
		L.setLat(-38.383494);
		L.setLng(33.427362);
		a.setLocation(L);
		
		Response res= given().queryParam("key", "qaclick123").body(a) 	// Simple call an object used to set the vaues in the body
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response();
		
		String responseString = res.asString();
		System.out.println(responseString);
		
}
}
