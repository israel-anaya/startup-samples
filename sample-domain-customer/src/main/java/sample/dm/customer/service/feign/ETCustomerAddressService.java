/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.dm.customer.service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.startupframework.feign.StartupClientConfig;

import sample.dm.customer.dto.CustomerAddressDTO;

@FeignClient(contextId = "etCustomerAddress", name = "sample-ms-et-customer", path = "/v1.0/customers", configuration = StartupClientConfig.class, primary = false)
public interface ETCustomerAddressService {

	@GetMapping("/{parentId}/addresses")
	List<CustomerAddressDTO> getAllItems(@PathVariable("parentId") String parentId);

	@GetMapping("/{parentId}/addresses/{childId}")
	CustomerAddressDTO getItem(@PathVariable("parentId") String parentId, @PathVariable("childId") String childId);

	@PostMapping("/{parentId}/addresses")
	CustomerAddressDTO createItem(@PathVariable("parentId") String parentId, CustomerAddressDTO item);

	@PutMapping("/{parentId}/addresses")
	CustomerAddressDTO updateItem(@PathVariable("parentId") String parentId, CustomerAddressDTO item);

	@DeleteMapping("/{parentId}/addresses/{childId}")
	CustomerAddressDTO deleteItem(@PathVariable("parentId") String parentId, @PathVariable("childId") String childId);
}
