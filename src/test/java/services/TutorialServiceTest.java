
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
import domain.Tutorial;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class TutorialServiceTest extends AbstractTest {

	@Autowired
	private TutorialService	tutorialService;


	//Test
	@Test
	public void testCreate() {
		Tutorial res = this.tutorialService.create();
		Assert.notNull(res);
	}

	@Test
	public void testSave() {
		Tutorial t = this.tutorialService.findOne(1078);
		t.setTitle("Aquí podría poner 33");
		this.tutorialService.save(t);
		Assert.isTrue(this.tutorialService.findAll().contains(t));
	}

	@Test
	public void testFindAll() {
		Collection<Tutorial> res = this.tutorialService.findAll();
		for (Tutorial t : res)
			System.out.println(t.getId() + " " + t.getTitle());
	}

	@Test
	public void testDelete() {
		Tutorial t;
		Collection<Tutorial> res;
		t = this.tutorialService.findOne(1078);
		this.tutorialService.delete(t);
		res = this.tutorialService.findAll();
		Assert.isTrue(!res.contains(t));
	}

}
