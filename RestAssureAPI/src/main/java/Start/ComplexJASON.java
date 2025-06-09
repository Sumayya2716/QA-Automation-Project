package Start;

import files.playload;
import io.restassured.path.json.JsonPath;

public class ComplexJASON {
	public static void main(String[] args) {
		int i;

		JsonPath js = new JsonPath(playload.CoursePrice());
		
		System.out.print("Print No of courses returned by API\n"); 
		int coursesize = js.getInt("courses.size()");
		System.out.println(coursesize+"\n");
		
		System.out.print("Print Purchase Amount\n"); 
		int TotalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(TotalAmount+"\n");
		
		System.out.print("Print Title of the first course\n"); 
		String FirstCourseTitle = js.getString("courses.title[0]");
		System.out.println(FirstCourseTitle+"\n");
		
		System.out.print("Print All course titles and their respective Prices\n");
		for(i=0; i<coursesize;i++) {
			String CourseTitle = js.get("courses["+i+"].title");
			System.out.print(CourseTitle+"\t");
			int Prices = js.getInt("courses.price["+i+"]");
			System.out.print(Prices+"\n");
		}
		
		System.out.print("Print no of copies sold by RPA Course\t");
		for( i=0;i<coursesize;i++) {
			String CourseTitle = js.get("courses["+i+"].title");
			if(CourseTitle.equalsIgnoreCase("RPA")){
				int Copies= js.getInt("courses.copies["+i+"]");
				System.out.print(Copies+"\n");
				break;
			}
		}
	}

}
