package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class DynamicJSON {
	
	@Test(dataProvider="BooksData")
	public void addbook(String isbn, String aisle)   {
		RestAssured.baseURI ="http://216.10.245.166"; 
		String response= given().header("Content_Type","application/json").body(playload.LibraryAPI(isbn, aisle))
		//.body(new String(Files.readAllBytes(Paths.get("C://Users//Owners/Document/AddPlace.json"))))
		.when().post("/Library/Addbook.php/")
		.then().assertThat().statusCode(200).log().all().extract().response().asString();
		System.out.println(response);
		
		JsonPath js = ReUsableMethods.RawToJSON(response);

		String ID =js.getString("ID");
		System.out.println("The ID is ::"+ID+ "\n");
		
	
	}
@DataProvider(name="BooksData")
public Object[][] getdata() {
	//array= collection of elements
	//Multidimensional array= collection of arrays
	
	return new Object[][] {{"qwoe","112"},{"mjyk","3042"},{"kiko","8987"}};
	
}
}
