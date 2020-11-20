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

package sample.ms.et.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.startupframework.controller.StartupController;
import org.startupframework.controller.service.CRUDControllerBase;

import sample.dm.user.dto.UserDTO;
import sample.ms.et.user.adapter.UserAdapter;

/**
 *
 * @author Arq. Jesús Israel Anaya Salazar
 */
@StartupController
@RestController
@RequestMapping("/v1.0/users")
class UserController extends CRUDControllerBase<UserDTO, UserAdapter> {

	@Autowired
	protected UserController(UserAdapter adapter) {
		super(adapter);
	}

	@Override
	protected void updateProperties(UserDTO source, UserDTO target) {

		updateProperty(source::getPassword, target::setPassword);
		
	}
}
