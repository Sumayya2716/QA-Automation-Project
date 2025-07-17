	package Start;
	
	import io.restassured.RestAssured;
	
	import io.restassured.path.json.JsonPath;
	
	import static io.restassured.RestAssured.*;
	import static org.hamcrest.Matchers.*;

import org.junit.Assert;

//import org.junit.Assert;

import files.ReUsableMethods;
import files.playload;
//import org.testng.Assert;
	
	
	
	
	
	public class Basics {
	
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			RestAssured.baseURI ="https://rahulshettyacademy.com"; 
			
			//POST the API Request
			String response = given().log().all().queryParam("key","qaclick123").header("Content_Type","application/json").body(playload.AddPlace())
			.when().post("/maps/api/place/add/json")
			.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)")
			.extract().response().asString();
			System.out.println(response);
			JsonPath js = new JsonPath(response);
			
			String PlaceID =js.getString("place_id");
			String NewAdress = "70 Summer walk, USA";
	
			System.out.println("The PlaceID is ::"+PlaceID+ "\n");
			
			//Update Place (PUT Method)
			
			given().log().all().queryParam("key", "qaclick123").header("Content_Type","application/json").body("{\r\n"
					+ "\r\n"
					+ "\"place_id\":\""+PlaceID+ "\",\r\n"
					+ "\r\n"
					+ "\"address\": \""+NewAdress+"\",\r\n"
					+ "\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "\r\n"
					+ "}")
			.when().put("/maps/api/place/update/json")
			.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
			
			//GET Place
			
			String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", PlaceID)
			.when().get("/maps/api/place/get/json")
			.then().assertThat().log().all().statusCode(200).extract().response().asString();
			System.out.println(getPlaceResponse);
	
			
			JsonPath js1 = ReUsableMethods.RawToJSON(getPlaceResponse);
			String Actualaddress =js1.getString("address"); //while taking address in the getString method we have see is there any parent for it.
			System.out.println("Actual is "+Actualaddress);
			Assert.assertEquals(Actualaddress,NewAdress);
	
			
			
		}
	
	}
