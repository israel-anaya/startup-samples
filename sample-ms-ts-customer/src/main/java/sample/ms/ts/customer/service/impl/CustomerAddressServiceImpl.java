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
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.startupframework.service.ChildDataTransferObjectServiceBase;

import sample.ms.ts.customer.dto.CustomerAddressDTO;
import sample.ms.ts.customer.service.CustomerAddressService;

/**
 *
 * @author Arq. Jesús Israel Anaya Salazar
 */
@Service
class CustomerAddressServiceImpl extends ChildDataTransferObjectServiceBase<CustomerAddressDTO>
		implements CustomerAddressService {

	protected CustomerAddressServiceImpl() {
		super(true);
	}

	@Override
	protected void onValidateEntity(CustomerAddressDTO item) {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected CustomerAddressDTO onSave(String parentId, String childId, CustomerAddressDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<CustomerAddressDTO> onFindAll(String parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Optional<CustomerAddressDTO> onFindById(String parentId, String childId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onDeleteById(String parentId, String childId) {
		// TODO Auto-generated method stub

	}



}
