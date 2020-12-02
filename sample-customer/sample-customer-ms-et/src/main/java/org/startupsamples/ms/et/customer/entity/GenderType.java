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

package org.startupsamples.ms.et.customer.entity;

import org.startupframework.data.entity.IntegerEnum;
import org.startupframework.data.entity.IntegerEnumJpaConverter;

/**
 *
 * @author Arq. Jesús Israel Anaya Salazar
 */
public enum GenderType implements IntegerEnum {

	Female(VALUES.FEMALE), Male(VALUES.MALE), Unknonw(VALUES.UNKNONW);

	public static class VALUES {
		public static final int FEMALE = 10;
		public static final int MALE = 20;
		public static final int UNKNONW = 30;
	}

	private final int value;

	private GenderType(final int value) {
		this.value = value;
	}

	@Override
	public int getValue() {
		return value;
	}

	public static class Converter extends IntegerEnumJpaConverter<GenderType> {
		public Converter() {
			super(GenderType.class);
		}
	}
}
