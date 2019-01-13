
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
import domain.Sponsorships;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SponsorshipsServiceTest extends AbstractTest {

	@Autowired
	private SponsorshipsService	sponsorshipsService;
	@Autowired
	private SponsorService		sponsorService;
	@Autowired
	private CreditCardService	creditCardService;


	//Test
	@Test
	public void testSave() {
		Sponsorships s = this.sponsorshipsService.findOne(1076);
		s.setBannerURL("https://www.bannerURLTest33.com");
		this.sponsorshipsService.save(s);
		Assert.isTrue(this.sponsorshipsService.findAll().contains(s));

	}
	@Test
	public void testCreate() {
		super.authenticate("sponsorUserName1");
		Sponsorships res = this.sponsorshipsService.create();
		Assert.notNull(res);
		super.authenticate(null);
	}

	@Test
	public void testFindAll() {
		Collection<Sponsorships> res = this.sponsorshipsService.findAll();
		for (Sponsorships s : res)
			System.out.println(s.getId() + " " + s.getBannerURL());
	}

	@Test
	public void testDelete() {
		Sponsorships s;
		Collection<Sponsorships> res;
		s = this.sponsorshipsService.findOne(1076);
		this.sponsorshipsService.delete(s);
		res = this.sponsorshipsService.findAll();
		Assert.isTrue(!res.contains(s));
	}

}
