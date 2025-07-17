import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class OAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response= given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

		.formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type","client_credentials")
		.formParam("scope","trust")
		.when().post("oauthapi/oauth2/resourceOwner/token")
		.then().assertThat().statusCode(200).log().all().extract().response().asString();
		
		JsonPath js = new JsonPath(response);
		String Access_token =js.getString("access_token");
		System.out.println("AccesToken "+Access_token +"\n\n");
		
		
		
		//Access the obtained access token
		
		given().param("access_token",Access_token)
		.when().get("oauthapi/getCourseDetails")
		.then().log().all();
	}

}
