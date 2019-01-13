
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
import domain.SocialProfile;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SocialProfileServiceTest extends AbstractTest {

	@Autowired
	private SocialProfileService	socialProfileService;


	@Test
	public void testCreate() {
		SocialProfile res = new SocialProfile();
		Assert.notNull(res);
		super.authenticate(null);
	}

	@Test
	public void testSave() {
		SocialProfile s, saved;
		Collection<SocialProfile> socialProfile;

		s = this.socialProfileService.findOne(1066);
		s.setNick("xx");
		s.setSocialNetworkName("xxxx");
		s.setProfileLink("https://www.x.com");

		saved = this.socialProfileService.save(s);
		socialProfile = this.socialProfileService.findAll();

		Assert.isTrue(socialProfile.contains(saved));
	}

	@Test
	public void testDelete() {
		SocialProfile s;
		Collection<SocialProfile> socialProfile;

		s = this.socialProfileService.findOne(1066);
		this.socialProfileService.delete(s);
		socialProfile = this.socialProfileService.findAll();

		Assert.isTrue(!socialProfile.contains(s));
	}

	@Test
	public void testFindAll() {
		Collection<SocialProfile> socialProfile = this.socialProfileService.findAll();

		for (SocialProfile s : socialProfile)
			System.out.println(s.getId() + " " + s.getNick());
	}
}
