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

package sample.ms.mts.customer.identity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.startupframework.controller.StartupController;
import org.startupframework.controller.StartupEndpoint;

import lombok.Getter;
import sample.dm.customer.dto.CustomerIdentityDTO;
import sample.dm.customer.dto.CustomerIdentityInfoDTO;
import sample.ms.mts.customer.identity.service.CustomerIdentityService;

/**
 *
 * @author Arq. Jesús Israel Anaya Salazar
 */
@StartupController
@RestController
@RequestMapping("/v1.0/customers-identity")
class CustomerIdentityController extends StartupEndpoint { // StartupEndpoint Error Handling for controllers
	
	@Getter
	CustomerIdentityService service;

	@Autowired
	protected CustomerIdentityController(CustomerIdentityService service) {
		this.service = service;
	}

	@PostMapping()
	public @ResponseBody ResponseEntity<CustomerIdentityInfoDTO> validate(@RequestBody CustomerIdentityDTO identity) {
		
		CustomerIdentityInfoDTO identityInfo = service.validate(identity);
		
		return new ResponseEntity<>(identityInfo, HttpStatus.OK);
	}
}
