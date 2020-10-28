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

package sample.ms.et.user.service.impl;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.startupframework.data.entity.OldNewPred;
import org.startupframework.data.service.EntityServiceBase;

import sample.ms.et.user.entity.ClientEntity;
import sample.ms.et.user.entity.ClientEntity_;
import sample.ms.et.user.service.ClientService;

/**
 *
 * @author Arq. Jesús Israel Anaya Salazar
 */
@Service
class ClientServiceImpl extends EntityServiceBase<ClientRepository, ClientEntity> implements ClientService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	protected ClientServiceImpl(ClientRepository repository) {
		super(repository);
	}

	final Consumer<ClientEntity> EXISTS_USER_NAME = e -> existsBy(ClientEntity_.clientId, e::getClientId);

	final OldNewPred<ClientEntity> NEED_USER_NAME = (o, n) -> !Objects.equals(o.getClientId(), n.getClientId());

	@Override
	protected void onValidateObject(ClientEntity entity) {

		Optional<ClientEntity> oldEntity = getRepository().findById(entity.getId());

		validateIf(NEED_USER_NAME, EXISTS_USER_NAME, oldEntity, entity);
	}

	@Override
	protected void onBeforeSave(ClientEntity entity) {
		entity.setClientSecret(bCryptPasswordEncoder.encode(entity.getClientSecret()));
		super.onBeforeSave(entity);
	}

	@Override
	public ClientEntity findByClientId(String clientId) {
		return getRepository().findByClientId(clientId);
	}

}
