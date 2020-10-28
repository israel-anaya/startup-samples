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

package sample.ms.et.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.startupframework.data.entity.EntityBase;
import org.startupframework.data.entity.id.EntityId;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Arq. Jesús Israel Anaya Salazar
 */

@Entity
@EntityId("US")
@Data()
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends EntityBase {

	public UserEntity() {
	}

	@Column(length = 30, unique = true, nullable = false)
	@NotEmpty
	@Size(max = 30)
	private String userName;

	@Column(length = 500, nullable = false)
	@NotEmpty
	@Size(max = 500)
	private String password;


}