
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
import domain.Phase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PhaseServiceTest extends AbstractTest {

	@Autowired
	private PhaseService	phaseService;


	//Test
	@Test
	public void testCreatePhase() {
		Phase res = this.phaseService.createPhase();
		Assert.notNull(res);
	}

	@Test
	public void testFindAllPhases() {
		Collection<Phase> res = this.phaseService.findAll();
		for (Phase p : res)
			System.out.println(p.getId() + " " + p.getTitle());
	}

	@Test
	public void testSavePhase() {
		Phase p = this.phaseService.findOne(1046);
		p.setTitle("Viva el 33");
		this.phaseService.save(p);
		Assert.isTrue(this.phaseService.findAll().contains(p));
	}

	@Test
	public void testDeletePhase() {
		Phase p = this.phaseService.findOne(1046);
		this.phaseService.delete(p);
		Collection<Phase> res = this.phaseService.findAll();
		Assert.isTrue(!res.contains(p));
	}
}
