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

package org.startupsamples.ms.ts.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.startupframework.service.feign.CRUDFeign;
import org.startupsamples.dm.customer.dto.CustomerDTO;
import org.startupsamples.dm.customer.service.feign.ETCustomerService;

@Service
class CustomerFeignAdapter implements CRUDFeign<CustomerDTO> {

	final ETCustomerService feign;

	@Autowired
	protected CustomerFeignAdapter(final ETCustomerService feign) {
		this.feign = feign;
	}

	@Override
	public List<CustomerDTO> getAllItems() {
		return feign.getAllItems();
	}

	@Override
	public CustomerDTO getItem(String id) {
		return feign.getItem(id);
	}

	@Override
	public CustomerDTO createItem(CustomerDTO item) {
		return feign.createItem(item);
	}

	@Override
	public CustomerDTO updateItem(CustomerDTO item) {
		return feign.updateItem(item);
	}

	@Override
	public CustomerDTO deleteItem(String id) {
		return feign.deleteItem(id);
	}
}
