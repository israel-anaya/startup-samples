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

package sample.ms.et.customer.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.startupframework.data.entity.EntityBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Arq. Jesús Israel Anaya Salazar
 */
@Entity
@Data()
@EqualsAndHashCode(callSuper = true)
public class CustomerAddressEntity extends EntityBase {

	public CustomerAddressEntity() {
	}

	@ManyToOne
	@NotNull
	private CustomerEntity customer;

	@Convert(converter = CustomerAddressType.Converter.class)
	@NotNull
	private CustomerAddressType type;

	@Column(length = 200, nullable = false)
	@NotEmpty
	@Size(max = 200)
	private String street; // Calle

	@Column(length = 20, nullable = false)
	@NotEmpty
	@Size(max = 20)	
	private String streetNumber; // Numero Exterior
	
	@Column(length = 100, nullable = false)
	@NotEmpty
	@Size(max = 100)	
	private String neighborhood; // Colonia
	
	@Column(length = 100, nullable = false)
	@NotEmpty
	@Size(max = 100)	
	private String town; // Delegación Municipio

	@Column(length = 200, nullable = false)
	@NotEmpty
	@Size(max = 200)
	private String city; // Ciudad

	@Column(length = 200, nullable = false)
	@NotEmpty
	@Size(max = 200)
	private String state; // Estado

	@Column(length = 5, nullable = false)
	@NotEmpty
	@Size(min = 5, max = 5)
	private String zipCode; // Código Postal

	@Column(length = 200, nullable = false)
	@NotEmpty
	@Size(max = 200)
	private String country; // Pais

	static final String PREFIX_ID = "ADDR";

	@Override
	protected String prefixId() {
		return PREFIX_ID;
	}


}