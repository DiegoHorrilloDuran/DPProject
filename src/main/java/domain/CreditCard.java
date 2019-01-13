
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class CreditCard extends DomainEntity {

	private String			holderName;
	private CreditCardBrand	brand;
	private Long			number;
	private Integer			expirationMonth;
	private Integer			expirationYear;
	private Integer			cw;


	@NotBlank
	public String getHolderName() {
		return this.holderName;
	}

	public void setHolderName(final String holderName) {
		this.holderName = holderName;
	}

	@Enumerated(EnumType.STRING)
	@NotNull
	public CreditCardBrand getBrand() {
		return this.brand;
	}

	public void setBrand(final CreditCardBrand brand) {
		this.brand = brand;
	}

	@NotNull
	public Long getNumber() {
		return this.number;
	}

	public void setNumber(final Long number) {
		this.number = number;
	}

	@NotNull
	public Integer getExpirationMonth() {
		return this.expirationMonth;
	}

	public void setExpirationMonth(final Integer expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	@NotNull
	public Integer getExpirationYear() {
		return this.expirationYear;
	}

	public void setExpirationYear(final Integer expirationYear) {
		this.expirationYear = expirationYear;
	}

	@Range(min = 100, max = 999)
	public Integer getCw() {
		return this.cw;
	}

	public void setCw(final Integer cw) {
		this.cw = cw;
	}
}
