
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class HandyWorker extends Actor {

	private String						make;
	public Collection<Application>		applications;
	public Curriculum					curriculum;
	public Collection<Note>				notes;
	public Collection<Complaint>		complaints;
	public Collection<Endorsement>		endorsementsSenders;
	public Collection<Endorsement>		endorsementsRecipients;
	public Collection<Workplan>			workplans;
	public Collection<FixUpTaskFinder>	fixUpTaskFinders;


	@OneToMany
	public Collection<FixUpTaskFinder> getFixUpTaskFinders() {
		return this.fixUpTaskFinders;
	}

	public void setFixUpTaskFinders(final Collection<FixUpTaskFinder> fixUpTaskFinders) {
		this.fixUpTaskFinders = fixUpTaskFinders;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Workplan> getWorkplans() {
		return this.workplans;
	}

	public void setWorkplans(final Collection<Workplan> workplans) {
		this.workplans = workplans;
	}

	@OneToMany(mappedBy = "handyWorkerRecipient")
	public Collection<Endorsement> getEndorsementsRecipients() {
		return this.endorsementsRecipients;
	}

	public void setEndorsementsRecipients(final Collection<Endorsement> endorsements2) {
		this.endorsementsRecipients = endorsements2;
	}

	@OneToMany(mappedBy = "handyWorkerSender")
	public Collection<Endorsement> getEndorsementsSenders() {
		return this.endorsementsSenders;
	}

	public void setEndorsementsSenders(final Collection<Endorsement> endorsements) {
		this.endorsementsSenders = endorsements;
	}

	@OneToMany
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
	public Curriculum getCurriculum() {
		return this.curriculum;
	}

	public void setCurriculum(final Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	@NotBlank
	public String getMake() {
		return this.make;
	}

	public void setMake(final String make) {
		this.make = make;
	}

	@OneToMany(mappedBy = "handyWorker")
	public Collection<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(final Collection<Application> applications) {
		this.applications = applications;
	}

}
