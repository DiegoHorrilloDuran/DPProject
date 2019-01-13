package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class SocialProfile extends DomainEntity {
	private String nick;
	private String socialNetworkName;
	private String profileLink;
	
	@NotBlank
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}
	@NotBlank
	public String getSocialNetworkName() {
		return socialNetworkName;
	}
	
	public void setSocialNetworkName(String socialNetworkName) {
		this.socialNetworkName = socialNetworkName;
	}
	@NotBlank
	@URL
	public String getProfileLink() {
		return profileLink;
	}
	
	public void setProfileLink(String profileLink) {
		this.profileLink = profileLink;
	}
	
	
}
