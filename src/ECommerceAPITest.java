import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetail;
import pojo.Orders;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ECommerceAPITest {
	
	public static void main (String[] args) {
		// TODO Auto-generated method stub 
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		
		// Create a LoginRequest object and set the email and password
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("anshika@gmail.com");
		loginRequest.setUserPassword("Iamking@000");
		
		// Set the request specification, log the request, and send the login request
		RequestSpecification reqLogin = given().log().all().spec(req).body(loginRequest);
		
		// Send the POST request and map the response to a LoginResponse object
		LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract().response()
				.as(LoginResponse.class);
		
		// Print the token and user ID from the login response
		System.out.println(loginResponse.getToken());
		String token = loginResponse.getToken();
		System.out.println(loginResponse.getUserId());
		String userId = loginResponse.getUserId();
		
		// Add Product 
		// Add Product API request setup with authorization header
		RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token)
				.build();
		
		// Set up the request for adding a product, including the product details and file upload
		RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq).param("productName", "Laptop")
		.param("productAddedBy", userId).param("productCategory", "fashion")
		.param("productSubCategory", "shirts").param("productPrice", "11500")
		.param("productDescription", "Lenova").param("productFor", "men")
		.multiPart("productImage", new File("C:\\Users\\ilsan\\test.jpg"));
		
		// Send the POST request to add the product and extract the response
		String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product")
		.then().log().all().extract().response().asString();
		
		JsonPath js = new JsonPath(addProductResponse);
		String productId = js.get("productId");
		System.out.println("productId= "+productId);
		
		// Create Order
		// Create Order API request setup with authorization header
		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).setContentType(ContentType.JSON)
				.build();
		
		// Create an OrderDetail object and set the country and productId
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCountry("India");
		orderDetail.setProductOrderedId(productId);
		
		// Create a list of OrderDetail objects
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail> ();
		orderDetailList.add(orderDetail);
		
		// Create an Orders object and set the orders list
		Orders orders = new Orders();
		orders.setOrders(orderDetailList);
		
		// Send the POST request to create the order and extract the response
		RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(orders);
		String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
		System.out.println(responseAddOrder);
		
	}
}
