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

import static java.util.Collections.emptyList;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.startupframework.data.entity.OldNewPred;
import org.startupframework.data.service.EntityServiceBase;

import sample.ms.et.user.entity.UserEntity;
import sample.ms.et.user.entity.UserEntity_;
import sample.ms.et.user.service.UserService;

/**
 *
 * @author Arq. Jesús Israel Anaya Salazar
 */
@Service
class UserServiceImpl extends EntityServiceBase<UserRepository, UserEntity> implements UserService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	protected UserServiceImpl(UserRepository repository) {
		super(repository);
	}

	final Consumer<UserEntity> EXISTS_USER_NAME = e -> existsBy(UserEntity_.userName, e::getUserName);

	final OldNewPred<UserEntity> NEED_USER_NAME = (o, n) -> !Objects.equals(o.getUserName(), n.getUserName());

	@Override
	protected void onValidateObject(UserEntity entity) {

		Optional<UserEntity> oldEntity = getRepository().findById(entity.getId());

		validateIf(NEED_USER_NAME, EXISTS_USER_NAME, oldEntity, entity);
	}

	@Override
	protected void onBeforeSave(UserEntity entity) {
		entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		super.onBeforeSave(entity);
	}

	@Override
	public UserEntity findByUserName(String userName) {
		return getRepository().findByUserName(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserEntity userEntity = getRepository().findByUserName(userName);
		if (userEntity == null) {
			throw new UsernameNotFoundException(userName);
		}
		return new User(userEntity.getUserName(), userEntity.getPassword(), emptyList());
	}
}
