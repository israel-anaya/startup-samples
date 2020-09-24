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

package sample.ms.mts.customer.identity.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.startupframework.exception.DataNotFoundException;
import org.startupframework.service.ObjectValidatorService;

import sample.dm.customer.dto.CustomerIdentityDTO;
import sample.dm.customer.dto.CustomerIdentityInfoDTO;
import sample.ms.mts.customer.identity.service.CustomerIdentityService;

/**
 *
 * @author Arq. Jesús Israel Anaya Salazar
 */
@Service
class CustomerIdentityServiceImpl extends ObjectValidatorService<CustomerIdentityDTO>
		implements CustomerIdentityService {

	List<String> curps = new ArrayList<>();
	List<String> rfcs = new ArrayList<>();

	protected CustomerIdentityServiceImpl() {
	}

	@PostConstruct
	void init() {
		addIdentity("AASJ790422HDFNLS01", "AASJ790422IB0");

	}

	private void addIdentity(String curp, String rfc) {
		curps.add(curp);
		rfcs.add(rfc);
	}

	@Override
	protected void onValidateObject(CustomerIdentityDTO item) {
		// Validate required or format
		this.validateObjectConstraints(item);		
	}

	@Override
	public CustomerIdentityInfoDTO validate(CustomerIdentityDTO dto) {
		this.onValidateObject(dto);

		if(!curps.contains(dto.getCurp())) {
			throw DataNotFoundException.from(dto.getCurp());
		}

		if(!rfcs.contains(dto.getRfc())) {
			throw DataNotFoundException.from(dto.getRfc());
		}

		CustomerIdentityInfoDTO result = new CustomerIdentityInfoDTO();
		result.setCurpStatus("Valid");
		result.setRfcStatus("Valid");
		
		return result;
	}

}
