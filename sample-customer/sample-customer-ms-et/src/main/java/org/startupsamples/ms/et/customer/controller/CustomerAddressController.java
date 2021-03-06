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

package org.startupsamples.ms.et.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.startupframework.controller.StartupController;
import org.startupframework.controller.service.CRUDChildControllerBase;
import org.startupsamples.dm.customer.def.CustomerDef;
import org.startupsamples.dm.customer.dto.CustomerAddressDTO;
import org.startupsamples.ms.et.customer.adapter.CustomerAddressAdapter;

/**
 *
 * @author Arq. Jesús Israel Anaya Salazar
 */
@StartupController
@RestController
@RequestMapping(CustomerDef.CUSTOMER_ADDRESS_PATH)
class CustomerAddressController extends CRUDChildControllerBase<CustomerAddressDTO, CustomerAddressAdapter> {

	@Autowired
	protected CustomerAddressController(CustomerAddressAdapter adapter) {
		super(adapter);
	}

	@Override
	protected void updateProperties(CustomerAddressDTO source, CustomerAddressDTO target) {

		updateProperty(source::getType, target::setType);
		updateProperty(source::getStreet, target::setStreet);
		updateProperty(source::getStreetNumber, target::setStreetNumber);
		updateProperty(source::getNeighborhood, target::setNeighborhood);
		updateProperty(source::getTown, target::setTown);
		updateProperty(source::getCity, target::setCity);
		updateProperty(source::getState, target::setState);
		updateProperty(source::getZipCode, target::setZipCode);
		updateProperty(source::getCountry, target::setCountry);
		updateProperty(source::getActive, target::setActive);

	}
}
