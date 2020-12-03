package org.startupsamples.ms.et.customer.api;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.startupframework.config.StartupProperties;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

abstract class APITest {

	private static RequestSpecBuilder builder = new RequestSpecBuilder();
	static ObjectMapper objectMapper = new ObjectMapper();
	
	@BeforeAll
	static void init(@Autowired StartupProperties startupProperties, @Value("${local.server.port}") int port) {
		Map<String, String> headers = new HashMap<>();

		startupProperties.getHeaders().getRequired().forEach(header -> headers.put(header, header));

		builder.setPort(port);
		builder.setContentType(ContentType.JSON);
		builder.addHeaders(headers);
		builder.addHeader(HttpHeaders.AUTHORIZATION, "DUMMY");
	}

	protected static RequestSpecification createSpec() {
		return given().spec(builder.build());
	}

	static <T> T readValue(String path, Class<T> valueType) throws IOException {
		Resource resource = new ClassPathResource(path);
		InputStream inputstream = resource.getInputStream();
		return objectMapper.readValue(inputstream, valueType);
	}
	
	protected <P> void assertProperty(Supplier<P> expected, Supplier<P> actual) {
		P expectedValue = expected.get();
		if (expectedValue != null) {
			P actualValue = actual.get();
			assertEquals(expectedValue, actualValue);
		}
	}

}
