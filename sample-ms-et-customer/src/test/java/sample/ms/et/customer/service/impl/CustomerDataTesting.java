package sample.ms.et.customer.service.impl;

import java.util.Date;

import sample.ms.et.customer.entity.CustomerEntity;
import sample.ms.et.customer.entity.GenderType;

public class CustomerDataTesting {
		
	public static String NUMBER = "0000000010"; // number
	public static String SUFFIX_NAME = "SR"; // suffixName
	public static String FIRST_NAMES = "Jesus Israel"; // firstNames
	public static String FIRST_SURNAME = "Anaya"; // firstSurname
	public static String SECOND_SURNAME = "Salazar"; // secondSurname
	public static Date BIRD_DATE = new Date(1979, 04, 22); // birthDate
	public static GenderType GENDER = GenderType.Male; // gender
	public static String CURP = "AASJ790422DUO"; // curp
	public static String TAX_ID = "AASJ790422DUO"; // taxId RFC
	public static String BIRTH_PLACE = "f4f881f3-76e5-4e9b-a4d6-8f8d5c960735"; // birthPlace
	public static String EMAIL = "israel.anaya@baas-platform.com"; // email
	public static String ID_NUMBER = "1234567890"; // idNumber
	
	public static CustomerEntity createEntity() {
		CustomerEntity entity = new CustomerEntity();
		entity.setNumber(NUMBER);
		entity.setSuffixName(SUFFIX_NAME);
		entity.setFirstNames(FIRST_NAMES);
		entity.setFirstSurname(FIRST_SURNAME);
		entity.setSecondSurname(SECOND_SURNAME);
		entity.setBirthDate(BIRD_DATE);
		entity.setGender(GENDER);
		entity.setCurp(CURP);
		entity.setTaxId(TAX_ID);
		entity.setBirthPlace(BIRTH_PLACE);
		entity.setEmail(EMAIL);
		entity.setIdNumber(ID_NUMBER);
		return entity;
	}
}
