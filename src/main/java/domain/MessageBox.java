
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import domain.DomainEntity;

@Entity
@Access(AccessType.PROPERTY)
public class MessageBox extends DomainEntity {

	private Boolean				defaultBox;
	private String				boxName;
	public Collection<Message>	messages;


	public String getBoxName() {
		return this.boxName;
	}

	public void setBoxName(final String boxName) {
		this.boxName = boxName;
	}

	@ManyToMany
	public Collection<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(final Collection<Message> messages) {
		this.messages = messages;
	}

	public Boolean getDefaultBox() {
		return this.defaultBox;
	}

	public void setDefaultBox(final Boolean defaultBox) {
		this.defaultBox = defaultBox;
	}

}
