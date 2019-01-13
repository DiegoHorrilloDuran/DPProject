package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.springframework.format.annotation.DateTimeFormat;

import domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class SearchResult extends DomainEntity {

	private Date searchDateTime;
	private Integer resultsNumber;
	public Collection<FixUpTask> fixUpTasks;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	public Collection<FixUpTask> getFixUpTasks() {
		return this.fixUpTasks;
	}

	public void setFixUpTasks(final Collection<FixUpTask> fixUpTasks) {
		this.fixUpTasks = fixUpTasks;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getSearchDateTime() {
		return this.searchDateTime;
	}

	public void setSearchDateTime(final Date searchDateTime) {
		this.searchDateTime = searchDateTime;
	}

	public Integer getResultsNumber() {
		return this.resultsNumber;
	}

	public void setResultsNumber(final Integer resultsNumber) {
		this.resultsNumber = resultsNumber;
	}

}
