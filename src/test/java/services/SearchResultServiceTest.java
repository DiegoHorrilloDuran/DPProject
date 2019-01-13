
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.FixUpTask;
import domain.SearchResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SearchResultServiceTest extends AbstractTest {

	@Autowired
	private SearchResultService	searchResultService;
	@Autowired
	private FixUpTaskService	fixUpTaskService;


	//Test

	@Test
	public void testCreate() {

		Collection<FixUpTask> f = this.fixUpTaskService.findAll();
		SearchResult res = this.searchResultService.create(f);
		Assert.notNull(res);
		super.authenticate(null);

	}
	@Test
	public void testFindAll() {
		Collection<SearchResult> res = this.searchResultService.findAll();

		for (SearchResult p : res)
			System.out.println(p.getId() + " " + p.getFixUpTasks());
	}

	@Test
	public void testSave() {
		SearchResult e, saved;
		Collection<SearchResult> searchResult;

		e = this.searchResultService.findOne(1052);
		e.setSearchDateTime(LocalDate.now().toDate());
		e.setResultsNumber(3);

		saved = this.searchResultService.save(e);
		searchResult = this.searchResultService.findAll();

		Assert.isTrue(searchResult.contains(saved));
	}

	@Test
	public void testDelete() {
		SearchResult sr;
		Collection<SearchResult> searchResult;

		sr = this.searchResultService.findOne(1052);
		this.searchResultService.delete(sr);
		searchResult = this.searchResultService.findAll();

		Assert.isTrue(!searchResult.contains(sr));
	}

}
