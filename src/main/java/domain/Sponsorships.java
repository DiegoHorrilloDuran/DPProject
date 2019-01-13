
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsorships extends DomainEntity {

	private String				bannerURL;
	private String				targetPageLink;
	public CreditCard			creditCard;
	public Sponsor				sponsor;
	public Collection<Tutorial>	tutorials;


	@OneToMany
	public Collection<Tutorial> getTutorials() {
		return this.tutorials;
	}

	public void setTutorials(final Collection<Tutorial> tutorials) {
		this.tutorials = tutorials;
	}

	@OneToOne(optional = true, mappedBy = "sponsorships")
	public Sponsor getSponsor() {
		return this.sponsor;
	}

	public void setSponsor(final Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	@NotBlank
	@URL
	public String getBannerURL() {
		return this.bannerURL;
	}

	public void setBannerURL(final String bannerURL) {
		this.bannerURL = bannerURL;
	}

	@NotBlank
	@URL
	public String getTargetPageLink() {
		return this.targetPageLink;
	}

	public void setTargetPageLink(final String targetPageLink) {
		this.targetPageLink = targetPageLink;
	}

	@OneToOne(optional = true, cascade = CascadeType.ALL)
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

}
