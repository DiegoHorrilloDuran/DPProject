
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class Workplan extends DomainEntity {

	public FixUpTask			fixUpTask;
	public Collection<Phase>	phases;


	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Phase> getPhases() {
		return this.phases;
	}

	public void setPhases(Collection<Phase> phases) {
		this.phases = phases;
	}

	@OneToOne(optional = false)
	public FixUpTask getFixUpTask() {
		return this.fixUpTask;
	}

	public void setFixUpTask(final FixUpTask fixUpTask) {
		this.fixUpTask = fixUpTask;
	}

}
