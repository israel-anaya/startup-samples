package org.startupsamples.ms.ts.customer.api;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.startupframework.config.StartupProperties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

abstract class APITest {

	private static RequestSpecBuilder builder = new RequestSpecBuilder();

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

	protected <P> void assertProperty(Supplier<P> expected, Supplier<P> actual) {
		P expectedValue = expected.get();
		if (expectedValue != null) {
			P actualValue = actual.get();
			assertEquals(expectedValue, actualValue);
		}
	}

}
