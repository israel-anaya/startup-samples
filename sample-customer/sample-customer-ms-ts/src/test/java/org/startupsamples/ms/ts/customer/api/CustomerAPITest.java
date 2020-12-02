package org.startupsamples.ms.ts.customer.api;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;

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
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.startupsamples.dm.customer.def.CustomerDef;
import org.startupsamples.dm.customer.dto.CustomerIdentityInfoDTO;
import org.startupsamples.dm.customer.service.feign.ETCustomerService;
import org.startupsamples.dm.customer.service.feign.MTCustomerIdentityService;
import org.startupsamples.ms.ts.customer.dto.CustomerAPIModel;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class CustomerAPITest extends APITest {

	static ObjectMapper objectMapper = new ObjectMapper();
	static String customerId;
	static CustomerAPIModel newCustomer;
	static CustomerAPIModel updateCustomer;

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

	static <T> T readValue(String path, Class<T> valueType) throws IOException {
		Resource resource = new ClassPathResource(path);
		InputStream inputstream = resource.getInputStream();
		return objectMapper.readValue(inputstream, valueType);
	}

	@BeforeAll
	static void initData() throws IOException {
		newCustomer = readValue("ts-customer-create.json", CustomerAPIModel.class);
		updateCustomer = readValue("ts-customer-update.json", CustomerAPIModel.class);

	}

	@Test
	@Order(1)
	void createItem() {

		CustomerIdentityInfoDTO identityResult = new CustomerIdentityInfoDTO();

		when(mtCustomerIdentityService.validate(any())).thenReturn(identityResult);

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.body(newCustomer);

		Response response;
		response = given().spec(requestSpecification).when().post(CustomerDef.CUSTOMER_PATH);
		response.then().statusCode(HttpStatus.CREATED.value());

		customerId = response.path("id");
		CustomerAPIModel actualCustomer = response.as(CustomerAPIModel.class);
		assertObject(newCustomer, actualCustomer);

	}

	@Test
	@Order(2)
	void updateItem() {

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.body(updateCustomer);

		Response response;
		response = given().spec(requestSpecification).when().put(CustomerDef.CUSTOMER_PATH);
		response.then().statusCode(HttpStatus.OK.value());

		CustomerAPIModel actualCustomer = response.as(CustomerAPIModel.class);
		assertObject(updateCustomer, actualCustomer);

	}

	@Test
	@Order(3)
	void getItem() {

		RequestSpecification requestSpecification = createSpec();
		requestSpecification.pathParam("id", customerId);

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
		requestSpecification.pathParam("id", customerId);

		Response response;
		response = given().spec(requestSpecification).when().delete(CustomerDef.CUSTOMER_PATH + "/{id}");
		response.then().statusCode(HttpStatus.OK.value());
	}

}
