
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsor extends Actor {

	public Sponsorships	sponsorships;


	@OneToOne(optional = false)
	public Sponsorships getSponsorships() {
		return this.sponsorships;
	}

	public void setSponsorships(final Sponsorships sponsorships) {
		this.sponsorships = sponsorships;
	}

}
