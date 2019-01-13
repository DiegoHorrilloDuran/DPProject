
package services;

import java.util.Collection;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.FixUpTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class FixUpTaskServiceTest extends AbstractTest {

	// Service under test
	@Autowired
	private FixUpTaskService	fixUpTaskService;


	// test

	@Test
	public void testCreateFixUpTask() {
		FixUpTask res = new FixUpTask();
		Assert.notNull(res);
	}

	@Test
	public void testFindAll() {
		Collection<FixUpTask> fixUpTasks = this.fixUpTaskService.findAll();
		for (FixUpTask f : fixUpTasks)
			System.out.println(f.getId() + " " + f.getCustomer().getName() + " " + f.getAddress());
	}

	@Test
	public void testSaveFixUpTask() {
		FixUpTask f, saved;
		Collection<FixUpTask> fixUpTasks;

		f = this.fixUpTaskService.findOne(974);
		f.setTicker("X");
		f.setPublishingDate(LocalDate.now().toDate());
		f.setDescription("X");
		f.setAddress("X");

		saved = this.fixUpTaskService.save(f);
		fixUpTasks = this.fixUpTaskService.findAll();

		Assert.isTrue(fixUpTasks.contains(saved));
	}

	@Test
	public void testDeleteFixUpTask() {
		FixUpTask d;
		Collection<FixUpTask> fixUpTasks;

		d = this.fixUpTaskService.findOne(974);
		this.fixUpTaskService.delete(d);
		fixUpTasks = this.fixUpTaskService.findAll();

		Assert.isTrue(!fixUpTasks.contains(d));
	}

}
