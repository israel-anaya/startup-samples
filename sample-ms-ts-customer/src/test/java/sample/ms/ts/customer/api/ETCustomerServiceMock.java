package sample.ms.ts.customer.api;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.startupframework.exception.DataNotFoundException;

import sample.dm.customer.dto.CustomerDTO;
import sample.dm.customer.service.feign.ETCustomerService;

public class ETCustomerServiceMock implements ETCustomerService {

	List<CustomerDTO> buffer = new ArrayList<>();

	CustomerDTO findItem(String id) {
		Optional<CustomerDTO> found = buffer.stream().filter(item -> item.getId().equals(id)).findFirst();
		return found.orElseThrow(() -> DataNotFoundException.fromId(id));
	}

	@Override
	public List<CustomerDTO> getAllItems() {
		return buffer;
	}

	@Override
	public CustomerDTO getItem(String id) {
		return findItem(id);
	}

	@Override
	public CustomerDTO createItem(CustomerDTO item) {
		Date currentDate = new Date(Instant.now().toEpochMilli());
		item.setId("CUST-7e6648f3-1fde-4239-bcb7-b6b39ec323b2");
		item.setCreatedDate(currentDate);
		item.setModifiedDate(currentDate);
		buffer.add(item);
		return item;
	}

	@Override
	public CustomerDTO updateItem(CustomerDTO item) {
		Date currentDate = new Date(Instant.now().toEpochMilli());
		item.setCreatedDate(currentDate);
		item.setModifiedDate(currentDate);
		return item;
	}

	@Override
	public CustomerDTO deleteItem(String id) {
		CustomerDTO item = findItem(id);
		buffer.remove(item);
		return item;
	}

}
