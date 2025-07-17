import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import POJO_Class.LoginRequestEcommerce;
import POJO_Class.LoginResponseEcommerce;
import POJO_Class.OrderDetailEcommerce;
import POJO_Class.OrderEcommerce;


public class EcommerceAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		
		//Login
		
		LoginRequestEcommerce loginRequest = new LoginRequestEcommerce();
		loginRequest.setUserEmail("sumayya2716@gmail.com");
		loginRequest.setUserPassword("Sum@yya1234");
		
		RequestSpecification reqLogin=given().relaxedHTTPSValidation().log().all().spec(req).body(loginRequest) ;
		LoginResponseEcommerce loginResponse=reqLogin.when().post("api/ecom/auth/login")
						.then().extract().response().as(LoginResponseEcommerce.class);
		System.out.println(loginResponse.getToken());
		String Token = loginResponse.getToken();
		System.out.println(loginResponse.getUserId());
		String UserId = loginResponse.getUserId();
		System.out.println(loginResponse.getMessage());

		
		//Create Product
		
		RequestSpecification CreateProductBaseReq =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", Token).build();
		
		RequestSpecification reqCreateProduct=given().log().all().spec(CreateProductBaseReq)
		.param("productName","qwerty")
		.param("productAddedBy",UserId)
		.param("productCategory","fashion")
		.param("productSubCategory","shirts")
		.param("productPrice","11500")
		.param("productDescription","Addias Originals")
		.param("productFor","women")
		.multiPart("productImage",new File("/Users/salmanahmed/Desktop/—Pngtree—blue graduation cap with diploma_20614294.png"));
		
		String CreateProductResponse=reqCreateProduct.when().post("api/ecom/product/add-product")
		.then().log().all().extract().response().asString();
                    
		JsonPath js= new JsonPath(CreateProductResponse);
		System.out.println("ProductId:"+js.get("productId"));
		String ProductID= js.get("productId");
		System.out.println("Message :"+js.get("message"));
		
		//Create Order
		RequestSpecification createOrderBaseReq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", Token).setContentType(ContentType.JSON) //Here we are sending Playload as Json so ContentType.JSON is required
				.build();
		
		OrderDetailEcommerce orderDetail = new OrderDetailEcommerce();
		orderDetail.setCountry("India");
		orderDetail.setProductOrderedId(ProductID);
		
		List<OrderDetailEcommerce> orderDetailList = new ArrayList<OrderDetailEcommerce> ();
		orderDetailList.add(orderDetail);	
		OrderEcommerce orderEcom =new OrderEcommerce();
		orderEcom.setOrders(orderDetailList);
		
		RequestSpecification createOrderReq=given().log().all().spec(createOrderBaseReq).body(orderEcom);
		String responseCreateOrder=createOrderReq.when().post("api/ecom/order/create-order").then().log().all().extract().asString();
		System.out.println(responseCreateOrder);
		
		
		//Delete Product

		RequestSpecification deleteProdBaseReq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addHeader("authorization", Token).setContentType(ContentType.JSON)
		.build();

		RequestSpecification deleteProdReq =given().log().all().spec(deleteProdBaseReq).pathParam("productId",ProductID); //Creating a path parameter

		String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().
		extract().response().asString();

		JsonPath js1 = new JsonPath(deleteProductResponse);

		Assert.assertEquals("Product Deleted Successfully",js1.get("message"));
		
		
	}
	


}
