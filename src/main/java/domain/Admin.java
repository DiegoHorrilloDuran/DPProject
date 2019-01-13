
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Admin extends Actor {

	public Collection<Category>		cataloguesOfCategories;
	public Collection<SearchResult>	searchresults;
	public Collection<WordsList>	wordsLists;
	public Collection<Endorsement>	endorsements;
	public Collection<Warranty>		cataloguesOfWarranties;


	@ManyToMany(cascade = {
		CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH
	})
	public Collection<Warranty> getCataloguesOfWarranties() {
		return this.cataloguesOfWarranties;
	}

	public void setCataloguesOfWarranties(final Collection<Warranty> cataloguesOfWarranties) {
		this.cataloguesOfWarranties = cataloguesOfWarranties;
	}

	@ManyToMany
	public Collection<Endorsement> getEndorsements() {
		return this.endorsements;
	}

	public void setEndorsements(final Collection<Endorsement> endorsements) {
		this.endorsements = endorsements;
	}

	@ManyToMany
	public Collection<WordsList> getWordsLists() {
		return this.wordsLists;
	}

	public void setWordsLists(final Collection<WordsList> wordsLists) {
		this.wordsLists = wordsLists;
	}

	@ManyToMany()
	public Collection<SearchResult> getSearchresults() {
		return this.searchresults;
	}

	public void setSearchresults(final Collection<SearchResult> searchresults) {
		this.searchresults = searchresults;
	}

	@ManyToMany
	public Collection<Category> getCataloguesOfCategories() {
		return this.cataloguesOfCategories;
	}

	public void setCataloguesOfCategories(final Collection<Category> cataloguesOfCategories) {
		this.cataloguesOfCategories = cataloguesOfCategories;
	}
}
