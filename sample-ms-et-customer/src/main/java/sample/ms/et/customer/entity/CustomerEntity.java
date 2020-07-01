package sample.ms.et.customer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
@EntityId("CUST")
@Data()
@EqualsAndHashCode(callSuper = true)
public class CustomerEntity extends EntityBase {

	public CustomerEntity() {
	}

	@Column(length = 30, unique = true, nullable = false)
	@NotEmpty
	@Size(max = 30)
	private String number;

	@Column(length = 10, nullable = false)
	@NotEmpty
	@Size(max = 10)
	private String suffixName;

	@Column(length = 50, nullable = false)
	@NotEmpty
	@Size(max = 50)
	private String firstNames;

	@Column(length = 50, nullable = true)
	@Size(max = 50)
	private String firstSurname;

	@Column(length = 50, nullable = false)
	@NotEmpty
	@Size(max = 50)
	private String secondSurname;

	@Column(nullable = false)
	@NotNull
	private Date birthDate;

	@NotNull
	@Convert(converter = GenderType.Converter.class)
	private GenderType gender;

	@Column(length = 20, unique = true, nullable = false)
	@NotEmpty
	@Size(min = 10, max = 20)
	private String curp;

	@Column(length = 15, unique = true, nullable = false)
	@NotEmpty
	@Size(min = 12, max = 15)
	private String taxId; // RFC

	@Column(length = ID_LENGTH, unique = false, nullable = false)
	@NotEmpty
	private String birthPlace; // Country Catalog

	@Column(length = 100, unique = false, nullable = false)
	@NotEmpty
	@Email
	@Size(max = 100)
	private String email;

	@Column(length = 20, unique = true, nullable = true)
	@NotEmpty
	@Size(max = 20)
	private String idNumber; // CI, CC, DNI, INE/IFE

}