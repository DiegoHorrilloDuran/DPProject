
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SearchResultRepository;
import domain.Admin;
import domain.FixUpTask;
import domain.FixUpTaskFinder;
import domain.SearchResult;

@Service
@Transactional
public class SearchResultService {

	@Autowired
	private SearchResultRepository	searchResultRepository;

	@Autowired
	private AdminService			adminService;

	@Autowired
	private FixUpTaskFinderService	fixUpTaskFinderService;


	public SearchResult create(Collection<FixUpTask> fut) {

		Date now = new Date(System.currentTimeMillis() - 1000);
		now.setHours(now.getHours() + 1);
		SearchResult res = new SearchResult();
		res.setFixUpTasks(fut);
		res.setSearchDateTime(now);
		res.setResultsNumber(fut.size());
		return res;
	}

	public SearchResult save(SearchResult searchResult) {
		Assert.notNull(searchResult);
		SearchResult srDef = this.searchResultRepository.save(searchResult);
		return srDef;
	}

	public SearchResult findOne(int id) {
		SearchResult res;
		res = this.searchResultRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public void delete(SearchResult s) {
		Assert.notNull(s);
		Collection<Admin> adm = this.adminService.findAll();
		for (Admin a : adm)
			if (a.getSearchresults().contains(s)) {
				a.getSearchresults().remove(s);
				this.adminService.save(a);
			}
		Collection<FixUpTaskFinder> fix = this.fixUpTaskFinderService.findAll();
		for (FixUpTaskFinder f : fix)
			if (f.getSearchResult().equals(s))
				this.fixUpTaskFinderService.delete(f);
		this.searchResultRepository.delete(s);
	}
	public Collection<SearchResult> findAll() {
		Collection<SearchResult> res;
		res = this.searchResultRepository.findAll();
		Assert.notNull(res);
		return res;
	}
}
