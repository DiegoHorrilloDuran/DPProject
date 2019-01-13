
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;

import domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {

	private String							ticker;
	public Collection<EducationRecord>		educationRecords;
	public Collection<ProfessionalRecord>	professionalRecords;
	public Collection<PersonalRecord>		personalRecords;
	public Collection<MiscellaneousRecord>	miscellaneousRecords;
	public Collection<EndorserRecord>		endorserRecords;
	public HandyWorker						handyWorker;


	@OneToMany(cascade = CascadeType.ALL)
	public Collection<EducationRecord> getEducationRecords() {
		return this.educationRecords;
	}

	public void setEducationRecords(final Collection<EducationRecord> eduactionRecords) {
		this.educationRecords = eduactionRecords;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<ProfessionalRecord> getProfessionalRecords() {
		return this.professionalRecords;
	}

	public void setProfessionalRecords(final Collection<ProfessionalRecord> professionalRecords) {
		this.professionalRecords = professionalRecords;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<PersonalRecord> getPersonalRecords() {
		return this.personalRecords;
	}

	public void setPersonalRecords(final Collection<PersonalRecord> personalRecords) {
		this.personalRecords = personalRecords;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<MiscellaneousRecord> getMiscellaneousRecords() {
		return this.miscellaneousRecords;
	}

	public void setMiscellaneousRecords(final Collection<MiscellaneousRecord> miscellaneousRecords) {
		this.miscellaneousRecords = miscellaneousRecords;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<EndorserRecord> getEndorserRecords() {
		return this.endorserRecords;
	}

	public void setEndorserRecords(final Collection<EndorserRecord> endorserRecords) {
		this.endorserRecords = endorserRecords;
	}

	@Column(unique = true)
	@NotBlank
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	@OneToOne(optional = false)
	public HandyWorker getHandyWorker() {
		return this.handyWorker;
	}
	public void setHandyWorker(HandyWorker handyWorker) {
		this.handyWorker = handyWorker;
	}

}
