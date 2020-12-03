package org.startupsamples.ms.et.customer.api;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.startupsamples.dm.customer.def.CustomerDef;
import org.startupsamples.dm.customer.dto.CustomerDTO;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class CustomerAPITest extends APITest {

	static String itemId;
	static CustomerDTO newItem;
	static CustomerDTO updateItem;
	
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

	@BeforeAll
	static void initData() throws IOException {
		newItem = readValue("customer-create.json", CustomerDTO.class);
		updateItem = readValue("customer-update.json", CustomerDTO.class);
	}
	
	@Test
	@Order(1)
	void createItem() {

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.body(newItem);

		Response response;
		response = given().spec(requestSpecification).when().post(CustomerDef.CUSTOMER_PATH);
		response.then().statusCode(HttpStatus.CREATED.value());

		itemId = response.path("id");
		CustomerDTO actualItem = response.as(CustomerDTO.class);
		assertObject(newItem, actualItem);

	}

	@Test
	@Order(2)
	void updateItem() {

		updateItem.setId(itemId);
		RequestSpecification requestSpecification = createSpec();
		requestSpecification.body(updateItem);

		Response response;
		response = given().spec(requestSpecification).when().put(CustomerDef.CUSTOMER_PATH);
		response.then().statusCode(HttpStatus.OK.value());
		
		CustomerDTO actualItem = response.as(CustomerDTO.class);
		assertObject(updateItem, actualItem);
	}

	@Test
	@Order(3)
	void getItem() {

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.pathParam("id", itemId);

		Response response;
		response = given().spec(requestSpecification).when().get(CustomerDef.CUSTOMER_PATH + "/{id}");
		response.then().statusCode(HttpStatus.OK.value());

	}

	@Test
	@Order(4)
	void findAllItems() {

		RequestSpecification requestSpecification = createSpec();

		Response response;
		response = given().spec(requestSpecification).when().get(CustomerDef.CUSTOMER_PATH);
		response.then().statusCode(HttpStatus.OK.value());
	}

	@Test
	@Order(5)
	void deleteItem() {

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.pathParam("id", itemId);

		Response response;
		response = given().spec(requestSpecification).when().delete(CustomerDef.CUSTOMER_PATH + "/{id}");
		response.then().statusCode(HttpStatus.OK.value());
	}

}
