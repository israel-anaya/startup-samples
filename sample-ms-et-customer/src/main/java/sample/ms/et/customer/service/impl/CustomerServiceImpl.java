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

package sample.ms.et.customer.service.impl;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.startupframework.data.entity.OldNewPred;
import org.startupframework.data.service.EntityServiceBase;

import sample.ms.et.customer.entity.CustomerEntity;
import sample.ms.et.customer.entity.CustomerEntity_;
import sample.ms.et.customer.service.CustomerService;

/**
 *
 * @author Arq. Jesús Israel Anaya Salazar
 */
@Service
class CustomerServiceImpl extends EntityServiceBase<CustomerRepository, CustomerEntity> implements CustomerService {


	@Autowired
	protected CustomerServiceImpl(CustomerRepository repository) {
		super(repository);
	}

	final Consumer<CustomerEntity> EXISTS_NUMBER_VALIDATOR = e -> existsBy(CustomerEntity_.number, e::getNumber);
	final Consumer<CustomerEntity> EXISTS_TAXID_VALIDATOR = e -> existsBy(CustomerEntity_.taxId, e::getTaxId);
	final Consumer<CustomerEntity> EXISTS_CURP_VALIDATOR = e -> existsBy(CustomerEntity_.curp, e::getCurp);
	final Consumer<CustomerEntity> EXISTS_IDNUM_VALIDATOR = e -> existsBy(CustomerEntity_.idNumber, e::getIdNumber);

	final OldNewPred<CustomerEntity> NEED_NUMBER = (o, n) -> !Objects.equals(o.getNumber(), n.getNumber());
	final OldNewPred<CustomerEntity> NEED_TAXID = (o, n) -> !Objects.equals(o.getTaxId(), n.getTaxId());
	final OldNewPred<CustomerEntity> NEED_CURP = (o, n) -> !Objects.equals(o.getCurp(), n.getCurp());
	final OldNewPred<CustomerEntity> NEED_IDNUM = (o, n) -> !Objects.equals(o.getIdNumber(), n.getIdNumber());

	@Override
	protected void onValidateEntity(CustomerEntity entity) {

		Optional<CustomerEntity> oldEntity = getRepository().findById(entity.getId());

		validateIf(NEED_NUMBER, EXISTS_NUMBER_VALIDATOR, oldEntity, entity);
		validateIf(NEED_TAXID, EXISTS_TAXID_VALIDATOR, oldEntity, entity);
		validateIf(NEED_CURP, EXISTS_CURP_VALIDATOR, oldEntity, entity);
		validateIf(NEED_IDNUM, EXISTS_IDNUM_VALIDATOR, oldEntity, entity);

	}

	@Override
	public CustomerEntity findByNumber(String number) {
		return getRepository().findByNumber(number);
	}

}
