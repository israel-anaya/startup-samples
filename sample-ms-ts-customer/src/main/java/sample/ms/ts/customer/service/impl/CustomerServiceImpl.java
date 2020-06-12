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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.startupframework.service.DataTransferObjectServiceBase;

import sample.dm.customer.service.client.ETCustomerService;
import sample.ms.ts.customer.dto.CustomerDTO;
import sample.ms.ts.customer.service.CustomerService;

/**
 *
 * @author Arq. Jesús Israel Anaya Salazar
 */
@Service
class CustomerServiceImpl extends DataTransferObjectServiceBase<CustomerDTO> implements CustomerService {

	@Autowired
	ETCustomerService etCustomerService;
	
	protected CustomerServiceImpl() {		
	}

	@Override
	protected void onValidateEntity(CustomerDTO item) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected CustomerDTO onSave(CustomerDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Optional<CustomerDTO> onFindById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<CustomerDTO> onFindAll() {
		//ResponseEntity<List<CustomerDTO>> buffer = etCustomerService.getAllItems();		
		return null;//buffer.getBody();
	}

	@Override
	protected void onDeleteById(String id) {
		// TODO Auto-generated method stub
		
	}

}
