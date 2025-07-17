package POJO_Class;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {
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
		
		
		 RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				 .setContentType(ContentType.JSON).build();
				    
		ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		RequestSpecification res=given().spec(req).body(a); //RequestSpecBuilder

		Response response =res.when().post("/maps/api/place/add/json"). //when() is separated
				
		then().spec(resspec).extract().response();		 //ResponseSpecBuilder
		
		
		
		String responseString = response.asString();
		System.out.println(responseString);
		
}
}
