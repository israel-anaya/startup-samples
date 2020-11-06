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

package sample.ms.ts.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.startupframework.controller.feign.CRUDControllerBase;

import sample.ms.ts.customer.dto.CustomerDTO;
import sample.ms.ts.customer.service.CustomerService;

/**
 *
 * @author Arq. Jesús Israel Anaya Salazar
 */
@RestController
@RequestMapping("/v1.0/customers")
@CrossOrigin(origins = "*")
class CustomerController extends CRUDControllerBase<CustomerDTO, CustomerService> {

	@Autowired
	protected CustomerController(CustomerService service) {
		super(service);
	}

	@Override
	protected void updateProperties(CustomerDTO source, CustomerDTO target) {

		updateProperty(source::getNumber, target::setNumber);
		updateProperty(source::getSuffixName, target::setSuffixName);
		updateProperty(source::getFirstNames, target::setFirstNames);
		updateProperty(source::getFirstSurname, target::setFirstSurname);
		updateProperty(source::getSecondSurname, target::setSecondSurname);
		updateProperty(source::getBirthDate, target::setBirthDate);
		updateProperty(source::getGender, target::setGender);
		updateProperty(source::getCurp, target::setCurp);
		updateProperty(source::getTaxId, target::setTaxId);
		updateProperty(source::getEmail, target::setEmail);
		updateProperty(source::getIdNumber, target::setIdNumber);
		updateProperty(source::getActive, target::setActive);
		updateProperty(source::getBirthPlace, target::setBirthPlace);

	}
}
