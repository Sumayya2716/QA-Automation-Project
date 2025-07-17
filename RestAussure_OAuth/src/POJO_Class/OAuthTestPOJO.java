package POJO_Class;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;


public class OAuthTestPOJO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response= given()
				.formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
				.formParam("grant_type","client_credentials")
				.formParam("scope","trust")
				.when().post("oauthapi/oauth2/resourceOwner/token")
				.then().assertThat().statusCode(200).log().all().extract().response().asString();
				
				JsonPath js = new JsonPath(response);
				String Access_token =js.getString("access_token");
				System.out.println("AccesToken "+Access_token +"\n\n");
		
		

		//Access the obtained access token
		
		GetCourse gc=given()
				.param("access_token",Access_token)
				.when().log().all().get("oauthapi/getCourseDetails").as(GetCourse.class);
		
		System.out.println("LinkedIn link is "+gc.getLinkedIn());
		System.out.println("Instructor name is "+gc.getInstructor());
	//	System.out.println("Course name is "+gc.getCourses().getApi().get(1).getCourseTitle()); //Here Course title is printed based on the index but in future the indexes may change in that case we should get course title dynamically without using index 

		List<api> apiCourses=gc.getCourses().getApi();
		for(int i=0;i<apiCourses.size();i++)
		{
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
					{
				System.out.println(apiCourses.get(i).getPrice());
					}
		}
		
		
		//Get the course names of webAutomation
		String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
		
		ArrayList<String> a= new ArrayList<String>();//In the real time if courses keeps in adding,so we need to use arraylist instead of array¸
		
		List<WebAutomation> w=gc.getCourses().getWebAutomation();
		
		for(int j=0;j<w.size();j++)
		{
			a.add(w.get(j).getCourseTitle());
		}
		
		List<String> expectedList=	Arrays.asList(courseTitles);//Converting array to arraylist
		
		Assert.assertTrue(a.equals(expectedList));

	}

}
