
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;
import domain.Actor;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ActorServiceTest extends AbstractTest {

	@Autowired
	private ActorService	actorService;


	@Test
	public void testSave() {

		super.authenticate("adminUserName1");
		Actor a = null;
		String name = "adminName 1";
		for (Actor ac : this.actorService.findAll())
			if (ac.getName().equals(name))
				a = ac;
		String name2 = "adminName10";
		a.setName(name2);
		Actor res = this.actorService.save(a);
		Assert.isTrue(res.getName().equals(name2));
		super.authenticate(null);
	}

	@Test
	public void testFindByUserAccount() {
		super.authenticate("adminUserName1");
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Actor res = this.actorService.findByUserAccount(userAccount);
		String name = "adminName 1";
		Assert.isTrue(res.getName().equals(name));
		super.authenticate(null);
	}

	@Test
	public void testFindByPrincipal() {

		super.authenticate("adminUserName1");
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();

		Actor a = this.actorService.findByPrincipal();
		Assert.isTrue(a.getUserAccount().equals(userAccount));
		super.authenticate(null);

	}

}
