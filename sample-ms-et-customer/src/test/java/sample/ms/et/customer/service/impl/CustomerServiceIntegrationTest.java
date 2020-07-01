package sample.ms.et.customer.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.startupframework.exception.DuplicateDataException;

import sample.ms.et.customer.entity.CustomerEntity;
import sample.ms.et.customer.service.CustomerService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CustomerServiceIntegrationTest {


	@Autowired
	CustomerService customerService;

	@Test
	@Order(1)
	void crud() {
		CustomerEntity entity = CustomerDataTesting.createEntity();
		
		// Test creation
		CustomerEntity savedEntity = customerService.save(entity);
		String id = savedEntity.getId();
		assertNotNull(id);
		
		// Test Find
		CustomerEntity foundEntity = customerService.findById(id);
		assertNotNull(foundEntity);
		
		// Assert important values
		assertEquals(CustomerDataTesting.FIRST_NAMES, foundEntity.getFirstNames());
		assertEquals(CustomerDataTesting.ID_NUMBER, foundEntity.getIdNumber());
		
		// Test Update
		foundEntity.setActive(false);
		
		savedEntity = customerService.save(foundEntity);
		assertNotNull(savedEntity);
	}
	
	@Test()
	@Order(2)
	void duplicateData() {
		
		CustomerEntity entity = CustomerDataTesting.createEntity();		
		assertThrows(DuplicateDataException.class, () -> customerService.save(entity) );

	}

}
