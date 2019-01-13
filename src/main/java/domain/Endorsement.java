
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Endorsement extends DomainEntity {

	private Date				date;
	private Collection<String>	comments;
	public Customer				customerSender;
	public HandyWorker			handyWorkerSender;
	public Customer				customerRecipient;
	public HandyWorker			handyWorkerRecipient;


	@ManyToOne(optional = true)
	public Customer getCustomerRecipient() {
		return this.customerRecipient;
	}

	public void setCustomerRecipient(final Customer recipient) {
		this.customerRecipient = recipient;
	}

	@ManyToOne(optional = true, cascade = CascadeType.ALL)
	public HandyWorker getHandyWorkerRecipient() {
		return this.handyWorkerRecipient;
	}

	public void setHandyWorkerRecipient(final HandyWorker recipient2) {
		this.handyWorkerRecipient = recipient2;
	}

	@ManyToOne(optional = true)
	public Customer getCustomerSender() {
		return this.customerSender;
	}

	public void setCustomerSender(final Customer sender) {
		this.customerSender = sender;
	}

	@ManyToOne(optional = true, cascade = CascadeType.ALL)
	public HandyWorker getHandyWorkerSender() {
		return this.handyWorkerSender;
	}

	public void setHandyWorkerSender(final HandyWorker sender2) {
		this.handyWorkerSender = sender2;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getDate() {
		return this.date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	@ElementCollection
	@NotEmpty
	public Collection<String> getComments() {
		return this.comments;
	}
	public void setComments(final Collection<String> comments) {
		this.comments = comments;
	}

}
