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

package sample.ms.et.customer.adapter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.startupframework.data.adapter.CRUDChildAdapter;
import org.startupframework.data.entity.DataConverter;

import sample.dm.customer.dto.CustomerAddressDTO;
import sample.ms.et.customer.entity.CustomerAddressEntity;
import sample.ms.et.customer.service.CustomerAddressService;

@Service
public class CustomerAddressAdapter
		extends CRUDChildAdapter<CustomerAddressDTO, CustomerAddressEntity, CustomerAddressService> {

	@Mapper
	public interface Converter extends DataConverter<CustomerAddressDTO, CustomerAddressEntity> {

		static final Converter INSTANCE = Mappers.getMapper(Converter.class);

		@Override
		@Mapping(target = "customer", ignore = true)
		CustomerAddressEntity toEntity(CustomerAddressDTO dto);

		@Override
		@Mapping(target = "childId", source = "id")
		CustomerAddressDTO toDataTransferObject(CustomerAddressEntity entity);
	}

	@Autowired
	protected CustomerAddressAdapter(CustomerAddressService service) {
		super(service, Converter.INSTANCE);
	}

}
