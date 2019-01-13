
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
import domain.Complaint;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ComplaintServiceTest extends AbstractTest {

	@Autowired
	private ComplaintService	complaintService;


	@Test
	public void testCreate() {
		super.authenticate("customerUserName1");
		Complaint c = this.complaintService.create();
		Assert.notNull(c);
		super.authenticate(null);
	}

	@Test
	public void testDelete() {
		super.authenticate("customerUserName1");
		Complaint c = null;
		String ticker = "181114-US2018";
		c = this.complaintService.findByTicker(ticker);
		Assert.notNull(c);
		this.complaintService.delete(c);
		Collection<Complaint> res = this.complaintService.findAll();
		Assert.isTrue(!(res.contains(c)));
		super.authenticate(null);

	}
	@Test
	public void testFindAllByCustomer() {

		super.authenticate("customerUserName1");
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Collection<Complaint> res = this.complaintService.findAllByCustomer();
		Complaint c = null;
		for (Complaint com : this.complaintService.findAll())
			if (com.getFixUpTask().getCustomer().getUserAccount().equals(userAccount))
				c = com;

		Assert.isTrue(res.contains(c));
		super.authenticate(null);
	}

	@Test
	public void testFindAllUnasigned() {

		super.authenticate("customerUserName1");
		Collection<Complaint> res = this.complaintService.findAllUnasigned();
		Assert.isTrue(res.isEmpty());
		super.authenticate(null);

	}

}
