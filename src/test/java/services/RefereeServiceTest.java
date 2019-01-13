
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Referee;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RefereeServiceTest extends AbstractTest {

	//Service under test -------------------------

	@Autowired
	private RefereeService	refereeService;


	@Test
	public void testDelete() {

		//Delete referee -> username = RefereeUserName2
		super.authenticate("RefereeUserName2");
		Referee a = this.refereeService.findByPrincipal();
		this.refereeService.delete(a);
		Collection<Referee> ad = this.refereeService.findAll();
		Assert.isTrue(!ad.contains(a));
		super.authenticate(null);

	}

	@Test
	public void testSave() {
		super.authenticate("RefereeUserName2");
		Referee a = this.refereeService.findByPrincipal();
		a.setAddress("Address2");
		Referee s = this.refereeService.save(a);
		Collection<Referee> res = this.refereeService.findAll();
		Assert.isTrue(res.contains(s));
		super.authenticate(null);

	}

	@Test
	public void testCreate() {
		Referee r = this.refereeService.create();
		Assert.notNull(r);
	}

}
