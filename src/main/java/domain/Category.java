
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	private String					name;
	public Category					father;
	public Collection<Category>		children;
	public Collection<FixUpTask>	fixUpTasks;


	@OneToMany(mappedBy = "category")
	public Collection<FixUpTask> getFixUpTasks() {
		return this.fixUpTasks;
	}

	public void setFixUpTasks(final Collection<FixUpTask> fixUpTasks) {
		this.fixUpTasks = fixUpTasks;
	}

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@ManyToOne
	public Category getFather() {
		return this.father;
	}

	public void setFather(final Category father) {
		this.father = father;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Category> getChildren() {
		return this.children;
	}

	public void setChildren(final Collection<Category> children) {
		this.children = children;
	}
}
