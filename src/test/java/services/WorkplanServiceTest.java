
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
import domain.Workplan;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class WorkplanServiceTest extends AbstractTest {

	@Autowired
	private WorkplanService		workplanService;
	@Autowired
	private FixUpTaskService	fixUpTaskService;
	@Autowired
	private PhaseService		phaseService;


	//Test
	//TEST CREATE & SAVE
	@Test
	public void create() {
		Workplan w = this.workplanService.create(this.fixUpTaskService.findOne(974));
		Assert.notNull(w);
	}
	@Test
	public void save() {
		Workplan w = this.workplanService.findOne(1093);
		Collection<Phase> p = w.getPhases();
		p.remove(this.phaseService.findOne(1047));
		w.setPhases(p);
		this.workplanService.save(w);
		Assert.isTrue(this.workplanService.findAll().contains(w));
	}
	@Test
	public void delete() {
		Workplan w = this.workplanService.findOne(1093);
		this.workplanService.delete(w);
		Assert.isTrue(!this.workplanService.findAll().contains(w));
	}
}
