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

package org.startupsamples.ms.ts.customer.dto;

import org.startupframework.dto.DataTransferObjectChild;
import org.startupframework.dto.EntityChildDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerAddressAPIModel extends EntityChildDTO implements DataTransferObjectChild {

	public CustomerAddressAPIModel() {
	}

	//private String type; // API support one address type
	private String street;
	private String streetNumber;
	private String neighborhood;
	private String municipality; // API support other name for town
	private String city;
	private String state;
	private String zipCode;
	private String country; 

}
