
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class WordsList extends DomainEntity {

	private Collection<String>	spamWordList;
	private Collection<String>	positiveWords;
	private Collection<String>	negativeWords;


	@ElementCollection
	@NotEmpty
	public Collection<String> getSpamWordList() {
		return this.spamWordList;
	}

	public void setSpamWordList(final Collection<String> spamWordList) {
		this.spamWordList = spamWordList;
	}

	@ElementCollection
	@NotEmpty
	public Collection<String> getPositiveWords() {
		return this.positiveWords;
	}

	public void setPositiveWords(final Collection<String> positiveWords) {
		this.positiveWords = positiveWords;
	}

	@ElementCollection
	@NotEmpty
	public Collection<String> getNegativeWords() {
		return this.negativeWords;
	}

	public void setNegativeWords(final Collection<String> negativeWords) {
		this.negativeWords = negativeWords;
	}

}
