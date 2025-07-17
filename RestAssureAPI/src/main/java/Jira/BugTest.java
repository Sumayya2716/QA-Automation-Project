package Jira;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BugTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://sumayya2716.atlassian.net/" ;
		
		String createIssueResponse= given().header("Content-Type","application/json").header("Authorization","Basic c3VtYXl5YTI3MTZAZ21haWwuY29tOkFUQVRUM3hGZkdGMEptUHROVFVURkl3NzM0NzZzZEM4bWJ3U3I5Zm1xM1kyM2pPd3FaOVhDMmFPQVhmU1JkWGRyaUx5ZjM5RV9jVnF0UXJ4aHZBWnZVNW90aXliQnpEQl8zNGFWVDl1U3B4dk4zVXY3OC1jYkNQdHJrVGhsQ3BrQ0NKQ1ZrS3QwY3otb3dNQU8tYThFMThVdjRBbWtJaUpJVzRfcFplNHFYZEZaR0NQdFpPWS1MST0wQ0I1RTYyNQ==")
		.body("{\n"
				+ "    \"fields\": {\n"
				+ "       \"project\":\n"
				+ "       {\n"
				+ "          \"key\": \"SCRUM\"\n"
				+ "       },\n"
				+ "       \"summary\": \"Website items are not working-Automation\",\n"
				+ "       \"issuetype\": {\n"
				+ "          \"name\": \"Task\"\n"
				+ "       }\n"
				+ "   }\n"
				+ "}")
		.log().all().
		when().post("/rest/api/3/issue")
		.then().assertThat().statusCode(201).log().all().extract().response().asString();
		
		JsonPath js = new JsonPath(createIssueResponse);	
		String issueId = js.getString("id");	
		System.out.println(issueId);
		
		//Add Screenshots
		
		given().
		pathParam("key", issueId).
		header("Authorization","Basic c3VtYXl5YTI3MTZAZ21haWwuY29tOkFUQVRUM3hGZkdGMEptUHROVFVURkl3NzM0NzZzZEM4bWJ3U3I5Zm1xM1kyM2pPd3FaOVhDMmFPQVhmU1JkWGRyaUx5ZjM5RV9jVnF0UXJ4aHZBWnZVNW90aXliQnpEQl8zNGFWVDl1U3B4dk4zVXY3OC1jYkNQdHJrVGhsQ3BrQ0NKQ1ZrS3QwY3otb3dNQU8tYThFMThVdjRBbWtJaUpJVzRfcFplNHFYZEZaR0NQdFpPWS1MST0wQ0I1RTYyNQ==")
		.multiPart("file",new File("'/Users/salmanahmed/Desktop/Screenshot 2025-06-23 at 3.28.15 PM.png'")).log().all()
		.post("/rest/api/3/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		

	}

}
