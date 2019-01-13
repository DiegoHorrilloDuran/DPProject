
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.WordsList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class WordsListServiceTest extends AbstractTest {

	@Autowired
	private WordsListService	wordsListService;


	//Test
	@Test
	public void create() {
		WordsList w = this.wordsListService.create();
		Assert.notNull(w);
	}

	@Test
	public void save() {
		WordsList w = this.wordsListService.findOne(1092);
		Collection<String> s = w.getNegativeWords();
		s.add("33");
		w.setNegativeWords(s);
		this.wordsListService.save(w);
		Assert.isTrue(this.wordsListService.findAll().contains(w));
	}

	@Test
	public void delete() {
		WordsList w = this.wordsListService.findOne(1092);
		this.wordsListService.delete(w);
		Assert.isTrue(!this.wordsListService.findAll().contains(w));
	}

}
