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

package sample.ms.et.user.adapter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.startupframework.data.adapter.EntityServiceAdapter;
import org.startupframework.data.entity.DataConverter;

import sample.dm.user.dto.UserDTO;
import sample.ms.et.user.entity.UserEntity;
import sample.ms.et.user.service.UserService;

@Service
public class UserAdapter extends EntityServiceAdapter<UserDTO, UserEntity, UserService> {

	@Mapper
	public interface Converter extends DataConverter<UserDTO, UserEntity> {
		static final Converter INSTANCE = Mappers.getMapper(Converter.class);
				
	}
	
	

	@Autowired
	protected UserAdapter(UserService service) {
		super(service, Converter.INSTANCE);
	}
}
