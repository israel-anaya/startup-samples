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
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.startupframework.dto.DTOConverter;
import org.startupframework.service.feign.CRUDChildFeignBase;

import sample.dm.customer.dto.CustomerAddressDTO;
import sample.ms.ts.customer.dto.CustomerAddressAPIModel;
import sample.ms.ts.customer.service.CustomerAddressService;

@Service
class CustomerAddressServiceImpl extends CRUDChildFeignBase<CustomerAddressDTO, CustomerAddressAPIModel>
		implements CustomerAddressService {

	@Mapper
	public interface Converter extends DTOConverter<CustomerAddressDTO, CustomerAddressAPIModel> {
		static final Converter INSTANCE = Mappers.getMapper(Converter.class);

		@Override
		@Mapping(target = "type", constant = "Legal")
		@Mapping(target = "town", source = "municipality")
		CustomerAddressDTO toSource(CustomerAddressAPIModel target);
		
		@Override
		@Mapping(target = "municipality", source = "town")
		CustomerAddressAPIModel toTarget(CustomerAddressDTO source);
		
	}

	@Autowired
	protected CustomerAddressServiceImpl(final CustomerAddressFeignAdapter feign) {
		super(feign, Converter.INSTANCE);
	}

	@Override
	protected void onValidateObject(CustomerAddressAPIModel dto) {
		// TODO Auto-generated method stub

	}

}
