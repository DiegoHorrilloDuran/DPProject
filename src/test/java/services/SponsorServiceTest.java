
package services;

import java.util.Collection;

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
import domain.Sponsor;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SponsorServiceTest extends AbstractTest {

	@Autowired
	private SponsorService	sponsorService;


	@Test
	public void testSave() {

		super.authenticate("sponsorUserName1");
		Sponsor s = null;
		String name = "sponsorName 1";
		for (Sponsor sc : this.sponsorService.findAll())
			if (sc.getName().equals(name))
				s = sc;
		String name2 = "cambioDeNombre";
		s.setName(name2);
		Sponsor res = this.sponsorService.save(s);
		Assert.isTrue(res.getName().equals(name2));
		super.authenticate(null);
	}

	@Test
	public void testFindByPrincipal() {

		super.authenticate("sponsorUserName1");
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();

		Sponsor a = this.sponsorService.findByPrincipal();
		Assert.isTrue(a.getUserAccount().equals(userAccount));
		super.authenticate(null);

	}
	@Test
	public void testCreate() {
		Sponsor res = this.sponsorService.createSponsor();
		Assert.notNull(res);
	}

	@Test
	public void testFindAll() {
		Collection<Sponsor> res = this.sponsorService.findAll();
		for (Sponsor s : res)
			System.out.println(s.getId() + " " + s.getName());
	}

	@Test
	public void testDelete() {
		Sponsor s;
		Collection<Sponsor> res;

		s = this.sponsorService.findOne(1105);
		this.sponsorService.delete(s);
		res = this.sponsorService.findAll();

		Assert.isTrue(!res.contains(s));
	}

}
