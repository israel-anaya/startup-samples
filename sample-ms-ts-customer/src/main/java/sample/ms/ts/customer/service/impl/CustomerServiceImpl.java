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

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.startupframework.dto.DTOConverter;
import org.startupframework.feign.service.CRUDFeignService;

import sample.dm.customer.dto.CustomerIdentityDTO;
import sample.dm.customer.dto.CustomerIdentityInfoDTO;
import sample.dm.customer.service.feign.ETCustomerService;
import sample.dm.customer.service.feign.MTCustomerIdentityService;
import sample.ms.ts.customer.dto.CustomerDTO;
import sample.ms.ts.customer.service.CustomerService;

/**
 *
 * @author Arq. Jesús Israel Anaya Salazar
 */
@Service
class CustomerServiceImpl
		extends CRUDFeignService<sample.dm.customer.dto.CustomerDTO, CustomerDTO, ETCustomerService>
		implements CustomerService {

	@Mapper
	public interface Converter extends DTOConverter<sample.dm.customer.dto.CustomerDTO, CustomerDTO> {
		static final Converter INSTANCE = Mappers.getMapper(Converter.class);

	}

	@Autowired
	MTCustomerIdentityService mtCustomerIdentityService;

	@Autowired
	protected CustomerServiceImpl(final ETCustomerService feign) {
		super(feign, Converter.INSTANCE);
	}

	@Override
	protected void onValidateObject(CustomerDTO item) {

		CustomerIdentityDTO identity = new CustomerIdentityDTO();
		identity.setCurp(item.getCurp());
		identity.setRfc(item.getTaxId());

		CustomerIdentityInfoDTO buffer = mtCustomerIdentityService.validate(identity);

	}

}
