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

package sample.dm.user.dto;

import javax.validation.constraints.Size;

import org.startupframework.dto.EntityDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends EntityDTO {

	public UserDTO() {
	}

	@Size(min = 8, max = 30)
	private String userName;

	@Size(min = 8, max = 12)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String oldPassword;
}
