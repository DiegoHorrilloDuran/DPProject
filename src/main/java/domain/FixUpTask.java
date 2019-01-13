
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class FixUpTask extends DomainEntity {

	private String					ticker;
	private Date					publishingDate;
	private String					description;
	private String					address;
	private Money					maxPrice;
	private Date					carryOutDate;
	public Collection<Complaint>	complaints;
	public Collection<Application>	applications;
	public Customer					customer;
	public Category					category;
	public Warranty					warranty;
	public Workplan					workplan;


	@OneToOne(optional = true, mappedBy = "fixUpTask")
	public Workplan getWorkplan() {
		return this.workplan;
	}

	public void setWorkplan(final Workplan workplan) {
		this.workplan = workplan;
	}

	@OneToOne(optional = false, mappedBy = "fixUpTask")
	public Warranty getWarranty() {
		return this.warranty;
	}

	public void setWarranty(final Warranty warranty) {
		this.warranty = warranty;
	}

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

	@ManyToOne(optional = false, cascade = CascadeType.PERSIST)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	@OneToMany(mappedBy = "fixUpTask")
	public Collection<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(final Collection<Application> applications) {
		this.applications = applications;
	}

	@OneToMany(mappedBy = "fixUpTask")
	public Collection<Complaint> getComplaints() {
		return this.complaints;
	}

	public void setComplaints(final Collection<Complaint> complaints) {
		this.complaints = complaints;
	}

	@Column(unique = true)
	@NotBlank
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getPublishingDate() {
		return this.publishingDate;
	}

	public void setPublishingDate(final Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotBlank
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public Money getMaxPrice() {
		return this.maxPrice;
	}

	public void setMaxPrice(final Money maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getCarryOutDate() {
		return this.carryOutDate;
	}

	public void setCarryOutDate(final Date carryOutDate) {
		this.carryOutDate = carryOutDate;
	}

}
