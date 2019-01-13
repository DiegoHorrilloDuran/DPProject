package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class Money {
	private Double amount;
	private String currency;
	private Integer vatTax;
	
	@Min(0)
	@Digits(integer = 9, fraction = 2)
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@NotBlank
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public Integer getVatTax() {
		return vatTax;
	}
	
	public void setVatTax(Integer vatTax) {
		this.vatTax = vatTax;
	}
	
	
}
