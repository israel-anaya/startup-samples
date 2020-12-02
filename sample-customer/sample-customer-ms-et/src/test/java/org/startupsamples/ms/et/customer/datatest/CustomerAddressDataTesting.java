package org.startupsamples.ms.et.customer.datatest;

import org.startupsamples.dm.customer.dto.CustomerAddressDTO;
import org.startupsamples.ms.et.customer.entity.CustomerAddressEntity;

public class CustomerAddressDataTesting {

	public static String TYPE = "Legal";
	public static String STREET = "Insurgentes Sur"; 
	public static String STREET_NUMBER = "3000"; 
	public static String NEIGHBORHOOD = "Guadalupe Inn"; 
	public static String TOWN = "Benito Juarez";
	public static String CITY = "México";
	public static String STATE = "Ciudad de México";
	public static String ZIP_CODE = "00013";
	public static String COUNTRY = "México";

	public static CustomerAddressEntity createEntity() {
		CustomerAddressEntity entity = new CustomerAddressEntity();

		return entity;
	}
	
	public static CustomerAddressDTO createDTO() {
		CustomerAddressDTO dto = new CustomerAddressDTO();
		dto.setType(TYPE);
		dto.setStreet(STREET);
		dto.setStreetNumber(STREET_NUMBER);
		dto.setNeighborhood(NEIGHBORHOOD);
		dto.setTown(TOWN);
		dto.setCity(CITY);
		dto.setState(STATE);
		dto.setZipCode(ZIP_CODE);
		dto.setCountry(COUNTRY);
		return dto;
	}
}
