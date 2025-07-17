package Start;

import org.testng.Assert;
import org.testng.annotations.Test;

//import org.junit.Test;
import files.playload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	int i;
	int sum;
	
	@Test
	public  void sumvalidation() {
	
			int sum = 0;
			JsonPath js=new JsonPath(playload.CoursePrice());
			int count=	js.getInt("courses.size()");
			for(int i=0;i<count;i++)
			{
				int price=js.getInt("courses["+i+"].price");
				int copies=js.getInt("courses["+i+"].copies");
				int amount = price * copies;
				System.out.println(amount);
				sum = sum + amount;
				
			}
			System.out.println(sum);
			int purchaseAmount =js.getInt("dashboard.purchaseAmount");
			Assert.assertEquals(sum, purchaseAmount);
		}

}
