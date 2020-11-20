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

package sample.ms.ts.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.startupframework.service.feign.CRUDChildFeign;

import sample.dm.customer.dto.CustomerAddressDTO;
import sample.dm.customer.service.feign.ETCustomerAddressService;

@Service
class CustomerAddressFeignAdapter implements CRUDChildFeign<CustomerAddressDTO> {

	final ETCustomerAddressService feign;
	
	@Autowired
	protected CustomerAddressFeignAdapter(final ETCustomerAddressService feign) {
		this.feign = feign;
	}

	@Override
	public List<CustomerAddressDTO> getAllItems(String parentId) {		
		return feign.getAllItems(parentId);
	}

	@Override
	public CustomerAddressDTO getItem(String parentId, String childId) {		
		return feign.getItem(parentId, childId);
	}

	@Override
	public CustomerAddressDTO createItem(String parentId, CustomerAddressDTO item) {
		return feign.createItem(parentId, item);
	}

	@Override
	public CustomerAddressDTO updateItem(String parentId, CustomerAddressDTO item) {
		return feign.updateItem(parentId, item);
	}

	@Override
	public CustomerAddressDTO deleteItem(String parentId, String childId) {		
		return feign.deleteItem(parentId, childId);
	}

}
