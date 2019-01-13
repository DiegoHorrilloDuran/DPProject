
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
import domain.FixUpTaskFinder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class FixUpTaskFinderServiceTest extends AbstractTest {

	@Autowired
	private FixUpTaskFinderService	fixUpTaskFinderService;


	//Test

	@Test
	public void testCreate() {

		FixUpTaskFinder res = this.fixUpTaskFinderService.create();
		Assert.notNull(res);
		super.authenticate(null);

	}

	@Test
	public void testFindAllFixUpTaskFinder() {
		Collection<FixUpTaskFinder> res = this.fixUpTaskFinderService.findAll();
		for (FixUpTaskFinder f : res)
			System.out.println(f.getId() + " " + f.getDescription());

		Assert.notNull(res);
		super.authenticate(null);

	}

	@Test
	public void testSaveFixUpTaskFinder() {
		Collection<FixUpTaskFinder> fixUpTaskFinders;
		FixUpTaskFinder h = this.fixUpTaskFinderService.findOne(1100);
		h.setDescription("hola");
		FixUpTaskFinder saved = this.fixUpTaskFinderService.save(h);
		fixUpTaskFinders = this.fixUpTaskFinderService.findAll();
		Assert.isTrue(fixUpTaskFinders.contains(saved));
	}

	@Test
	public void testDeleteFixUpTaskFinder() {

		FixUpTaskFinder f;
		f = this.fixUpTaskFinderService.findOne(1100);
		this.fixUpTaskFinderService.delete(f);
		Collection<FixUpTaskFinder> ff2 = this.fixUpTaskFinderService.findAll();
		Assert.isTrue(!ff2.contains(f));

	}
}
