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

package org.startupsamples.dm.customer.dto;

import java.util.Date;

import org.startupframework.dto.EntityDTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerDTO extends EntityDTO {
	
	public CustomerDTO() {		
	}
	
	private String number;
	private String suffixName;
	private String firstNames;
	private String firstSurname;
	private String secondSurname;
	
	@JsonFormat(pattern = DATE_PATTERN)
	private Date birthDate;

	private String gender;
	private String curp;	
	private String taxId;
	
	private String birthPlace;
	private String email;
	private String idNumber;

}
