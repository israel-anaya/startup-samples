package sample.ms.et.customer.api;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import sample.dm.customer.dto.CustomerAddressDTO;
import sample.ms.et.customer.datatest.CustomerAddressDataTesting;
import sample.ms.et.customer.datatest.CustomerDataTesting;
import sample.ms.et.customer.entity.CustomerEntity;
import sample.ms.et.customer.service.CustomerService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class CustomerAddressAPITest extends APITest {

	static String customerId;
	static String customerAddressId;

	void assertObject(CustomerAddressDTO expected, CustomerAddressDTO actual) {

		assertProperty(expected::getType, actual::getType);
		assertProperty(expected::getStreet, actual::getStreet);
		assertProperty(expected::getStreetNumber, actual::getStreetNumber);
		assertProperty(expected::getNeighborhood, actual::getNeighborhood);
		assertProperty(expected::getTown, actual::getTown);
		assertProperty(expected::getCity, actual::getCity);
		assertProperty(expected::getState, actual::getState);
		assertProperty(expected::getZipCode, actual::getZipCode);
		assertProperty(expected::getCountry, actual::getCountry);
		assertProperty(expected::getActive, actual::getActive);

	}

	@BeforeAll
	static void createCustomer(@Autowired CustomerService customerService) {
		CustomerEntity entity = CustomerDataTesting.createEntity();
		entity = customerService.save(entity);
		customerId = entity.getId();
	}

	@AfterAll
	static void deleteCustomer(@Autowired CustomerService customerService) {
		customerService.deleteById(customerId);
	}

	@Test
	@Order(1)
	void createItem() {

		CustomerAddressDTO newCustomerAddress = CustomerAddressDataTesting.createDTO();

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.pathParam("customerId", customerId);
		requestSpecification.body(newCustomerAddress);

		Response response;
		response = given().spec(requestSpecification).when().post("/v1.0/customers/{customerId}/addresses");
		response.then().statusCode(HttpStatus.CREATED.value());

		customerAddressId = response.path("childId");
		CustomerAddressDTO actualCustomerAddress = response.as(CustomerAddressDTO.class);
		assertObject(newCustomerAddress, actualCustomerAddress);

	}

	@Test
	@Order(2)
	void updateItem() {

		CustomerAddressDTO updateCustomerAddress = new CustomerAddressDTO();
		updateCustomerAddress.setChildId(customerAddressId);
		updateCustomerAddress.setActive(false);

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.pathParam("customerId", customerId);
		requestSpecification.body(updateCustomerAddress);

		Response response;
		response = given().spec(requestSpecification).when().put("/v1.0/customers/{customerId}/addresses");
		response.then().statusCode(HttpStatus.OK.value());

		CustomerAddressDTO actualCustomerAddress = response.as(CustomerAddressDTO.class);
		assertObject(updateCustomerAddress, actualCustomerAddress);
	}

	@Test
	@Order(3)
	void getItem() {

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.pathParam("customerId", customerId);
		requestSpecification.pathParam("id", customerAddressId);

		Response response;
		response = given().spec(requestSpecification).when().get("/v1.0/customers/{customerId}/addresses/{id}");
		response.then().statusCode(HttpStatus.OK.value());

	}

	@Test
	@Order(4)
	void findAllItems() {

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.pathParam("customerId", customerId);

		Response response;
		response = given().spec(requestSpecification).when().get("/v1.0/customers/{customerId}/addresses");
		response.then().statusCode(HttpStatus.OK.value());
	}

	@Test
	@Order(5)
	void deleteItem() {

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.pathParam("customerId", customerId);
		requestSpecification.pathParam("id", customerAddressId);

		Response response;
		response = given().spec(requestSpecification).when().delete("/v1.0/customers/{customerId}/addresses/{id}");
		response.then().statusCode(HttpStatus.OK.value());

	}

}
