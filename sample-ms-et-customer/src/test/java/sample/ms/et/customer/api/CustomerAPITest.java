package sample.ms.et.customer.api;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import sample.dm.customer.dto.CustomerDTO;
import sample.ms.et.customer.datatest.CustomerDataTesting;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class CustomerAPITest extends APITest {

	static String customerId;

	void assertObject(CustomerDTO expected, CustomerDTO actual) {
		assertProperty(expected::getNumber, actual::getNumber);
		assertProperty(expected::getSuffixName, actual::getSuffixName);
		assertProperty(expected::getFirstNames, actual::getFirstNames);
		assertProperty(expected::getFirstSurname, actual::getFirstSurname);
		assertProperty(expected::getSecondSurname, actual::getSecondSurname);
		assertProperty(expected::getBirthDate, actual::getBirthDate);
		assertProperty(expected::getGender, actual::getGender);
		assertProperty(expected::getCurp, actual::getCurp);
		assertProperty(expected::getTaxId, actual::getTaxId);
		assertProperty(expected::getEmail, actual::getEmail);
		assertProperty(expected::getIdNumber, actual::getIdNumber);
		assertProperty(expected::getActive, actual::getActive);
		assertProperty(expected::getBirthPlace, actual::getBirthPlace);
	}

	@Test
	@Order(1)
	void createItem() {

		CustomerDTO newCustomer = CustomerDataTesting.createDTO();

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.body(newCustomer);

		Response response;
		response = given().spec(requestSpecification).when().post("/v1.0/customers");
		response.then().statusCode(HttpStatus.CREATED.value());

		customerId = response.path("id");
		CustomerDTO actualCustomer = response.as(CustomerDTO.class);
		assertObject(newCustomer, actualCustomer);

	}

	@Test
	@Order(2)
	void updateItem() {

		CustomerDTO updateCustomer = new CustomerDTO();
		updateCustomer.setId(customerId);
		updateCustomer.setActive(false);

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.body(updateCustomer);

		Response response;
		response = given().spec(requestSpecification).when().patch("/v1.0/customers");
		response.then().statusCode(HttpStatus.OK.value());
		
		CustomerDTO actualCustomer = response.as(CustomerDTO.class);
		assertObject(updateCustomer, actualCustomer);
	}

	@Test
	@Order(3)
	void getItem() {

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.pathParam("id", customerId);

		Response response;
		response = given().spec(requestSpecification).when().get("/v1.0/customers/{id}");
		response.then().statusCode(HttpStatus.OK.value());

	}

	@Test
	@Order(4)
	void findAllItems() {

		RequestSpecification requestSpecification = createSpec();

		Response response;
		response = given().spec(requestSpecification).when().get("/v1.0/customers");
		response.then().statusCode(HttpStatus.OK.value());
	}

	@Test
	@Order(5)
	void deleteItem() {

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.pathParam("id", customerId);

		Response response;
		response = given().spec(requestSpecification).when().delete("/v1.0/customers/{id}");
		response.then().statusCode(HttpStatus.OK.value());
	}

}
