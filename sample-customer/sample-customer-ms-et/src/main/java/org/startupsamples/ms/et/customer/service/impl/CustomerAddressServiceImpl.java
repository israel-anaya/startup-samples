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

package org.startupsamples.ms.et.customer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.startupframework.data.service.EntityChildServiceBase;
import org.startupframework.exception.DataNotFoundException;
import org.startupsamples.ms.et.customer.entity.CustomerAddressEntity;
import org.startupsamples.ms.et.customer.entity.CustomerEntity;
import org.startupsamples.ms.et.customer.service.CustomerAddressService;

/**
 *
 * @author Arq. Jesús Israel Anaya Salazar
 */
@Service
class CustomerAddressServiceImpl extends EntityChildServiceBase<CustomerAddressRepository, CustomerAddressEntity>
		implements CustomerAddressService {

	@Autowired
	CustomerRepository parentRepository;

	@Autowired
	protected CustomerAddressServiceImpl(CustomerAddressRepository repository) {
		super(repository, true);
	}

	@Override
	protected String getParentId(CustomerAddressEntity entity) {
		return entity.getCustomer().getId();
	}

	@Override
	protected void setParentId(String parentId, CustomerAddressEntity entity) {
		Optional<CustomerEntity> foundItem = parentRepository.findById(parentId);
		CustomerEntity customerEntity;
		customerEntity = foundItem.orElseThrow(() -> DataNotFoundException.fromId(parentId));

		entity.setCustomer(customerEntity);
	}

	@Override
	protected String getChildId(CustomerAddressEntity entity) {
		return entity.getId();
	}

	@Override
	protected void setChildId(String childId, CustomerAddressEntity entity) {
		entity.setId(childId);
	}

	@Override
	public List<CustomerAddressEntity> onFindAll(String parentId) {
		return getRepository().findByCustomerId(parentId);
	}

	@Override
	public Optional<CustomerAddressEntity> onFindById(String parentId, String childId) {
		return getRepository().findByCustomerIdAndId(parentId, childId);
	}

}
