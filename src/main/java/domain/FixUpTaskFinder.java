
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class FixUpTaskFinder extends DomainEntity {

	private String		tickerKeyWord;
	private String		description;
	private String		address;
	private String		category;
	private String		warranty;
	private Money		minPrice;
	private Money		maxPrice;
	private Date		minDate;
	private Date		maxDate;
	public SearchResult	searchResult;


	@OneToOne(optional = false)
	public SearchResult getSearchResult() {
		return this.searchResult;
	}

	public void setSearchResult(final SearchResult searchResult) {
		this.searchResult = searchResult;
	}

	public String getTickerKeyWord() {
		return this.tickerKeyWord;
	}

	public void setTickerKeyWord(final String tickerKeyWord) {
		this.tickerKeyWord = tickerKeyWord;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public String getWarranty() {
		return this.warranty;
	}

	public void setWarranty(final String warranty) {
		this.warranty = warranty;
	}

	@AttributeOverrides({
		@AttributeOverride(name = "amount", column = @Column(name = "minAmount")), @AttributeOverride(name = "currency", column = @Column(name = "minCurrency")), @AttributeOverride(name = "vatTax", column = @Column(name = "minTax"))
	})
	public Money getMinPrice() {
		return this.minPrice;
	}

	public void setMinPrice(final Money minPrice) {
		this.minPrice = minPrice;
	}
	@AttributeOverrides({
		@AttributeOverride(name = "amount", column = @Column(name = "maxAmount")), @AttributeOverride(name = "currency", column = @Column(name = "maxCurrency")), @AttributeOverride(name = "vatTax", column = @Column(name = "maxTax"))
	})
	public Money getMaxPrice() {
		return this.maxPrice;
	}

	public void setMaxPrice(final Money maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMinDate() {
		return this.minDate;
	}

	public void setMinDate(final Date minDate) {
		this.minDate = minDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMaxDate() {
		return this.maxDate;
	}

	public void setMaxDate(final Date maxDate) {
		this.maxDate = maxDate;
	}

}
