
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	public CreditCard				creditCard;
	public Collection<Note>			notes;
	public Collection<Complaint>	complaints;
	public Collection<FixUpTask>	fixUpTasks;
	public Collection<Endorsement>	endorsementsSenders;
	public Collection<Endorsement>	endorsementsRecipients;


	@OneToMany(mappedBy = "customerRecipient")
	public Collection<Endorsement> getEndorsementsRecipients() {
		return this.endorsementsRecipients;
	}

	public void setEndorsementsRecipients(final Collection<Endorsement> endorsements2) {
		this.endorsementsRecipients = endorsements2;
	}

	@OneToMany(mappedBy = "customerSender")
	public Collection<Endorsement> getEndorsementsSenders() {
		return this.endorsementsSenders;
	}

	public void setEndorsementsSenders(final Collection<Endorsement> endorsements) {
		this.endorsementsSenders = endorsements;
	}

	@OneToMany(mappedBy = "customer")
	public Collection<FixUpTask> getFixUpTasks() {
		return this.fixUpTasks;
	}

	public void setFixUpTasks(final Collection<FixUpTask> fixUpTasks) {
		this.fixUpTasks = fixUpTasks;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Complaint> getComplaints() {
		return this.complaints;
	}

	public void setComplaints(final Collection<Complaint> complaints) {
		this.complaints = complaints;
	}

	@OneToMany
	public Collection<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(final Collection<Note> notes) {
		this.notes = notes;
	}

	@OneToOne(optional = true, cascade = CascadeType.ALL)
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

}
