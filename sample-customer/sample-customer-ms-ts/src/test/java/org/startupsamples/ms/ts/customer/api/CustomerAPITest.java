package org.startupsamples.ms.ts.customer.api;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.startupsamples.dm.customer.def.CustomerDef;
import org.startupsamples.dm.customer.dto.CustomerIdentityInfoDTO;
import org.startupsamples.dm.customer.service.feign.ETCustomerService;
import org.startupsamples.dm.customer.service.feign.MTCustomerIdentityService;
import org.startupsamples.ms.ts.customer.dto.CustomerAPIModel;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class CustomerAPITest extends APITest {

	static String itemId;
	static CustomerAPIModel newItem;
	static CustomerAPIModel updateItem;

	@MockBean
	MTCustomerIdentityService mtCustomerIdentityService;

	@TestConfiguration
	static class Configuration {

		@Bean
		@Primary
		ETCustomerService etCustomerService() {
			return new ETCustomerServiceMock();
		}
	}

	void assertObject(CustomerAPIModel expected, CustomerAPIModel actual) {
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
		newItem = readValue("customer-create.json", CustomerAPIModel.class);
		updateItem = readValue("customer-update.json", CustomerAPIModel.class);
	}

	@Test
	@Order(1)
	void createItem() {

		CustomerIdentityInfoDTO identityResult = new CustomerIdentityInfoDTO();

		when(mtCustomerIdentityService.validate(any())).thenReturn(identityResult);

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.body(newItem);

		Response response;
		response = given().spec(requestSpecification).when().post(CustomerDef.CUSTOMER_PATH);
		response.then().statusCode(HttpStatus.CREATED.value());

		itemId = response.path("id");
		CustomerAPIModel actualItem = response.as(CustomerAPIModel.class);
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

		CustomerAPIModel actualItem = response.as(CustomerAPIModel.class);
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
