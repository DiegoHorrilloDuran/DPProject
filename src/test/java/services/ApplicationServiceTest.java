
package services;

import java.util.ArrayList;
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
import domain.Application;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Status;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ApplicationServiceTest extends AbstractTest {

	//Service under test -------------------------

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private FixUpTaskService	fixUpTaskService;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	@Test
	public void createApplication() {
		super.authenticate("handyWorkerUserName2");
		String ticker = "161222-USADFS";
		FixUpTask fix = null;
		for (FixUpTask f : this.fixUpTaskService.findAll())
			if (f.getTicker().equals(ticker))
				fix = f;
		String name = "handyWorkerName 2";
		HandyWorker hw = null;
		for (HandyWorker h : this.handyWorkerService.findAll())
			if (h.getName().equals(name))
				hw = h;
		Application res = this.applicationService.create(hw, fix);
		Assert.notNull(res);
		super.authenticate(null);

	}

	@Test
	public void testDeleteApplication() {

		super.authenticate("handyWorkerUserName2");
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Application a = null;
		for (Application app : this.applicationService.findAll())
			if (app.getHandyWorker().getUserAccount().equals(userAccount))
				a = app;
		this.applicationService.delete(a);
		Collection<Application> res = this.applicationService.findAll();
		Assert.isTrue(!res.contains(a));
		super.authenticate(null);
	}

	@Test
	public void testCustomerUpdateApplicationStatusRejected() {

		super.authenticate("customerUserName1");
		//Customer1
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Application a = null;
		for (Application app : this.applicationService.findAll())
			if (app.getFixUpTask().getCustomer().getUserAccount().equals(userAccount))
				a = app;
		Application res = this.applicationService.customerUpdateApplicationStatusRejected(a, Status.REJECTED, "comment");
		Assert.isTrue(res.getStatus().equals(Status.REJECTED));
		super.authenticate(null);

	}

	@Test
	public void testCustomerUpdateApplicationStatusAccepted() {

		super.authenticate("customerUserName1");
		//Customer1
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Application a = null;
		for (Application app : this.applicationService.findAll())
			if (app.getFixUpTask().getCustomer().getUserAccount().equals(userAccount))
				a = app;
		Application res = this.applicationService.customerUpdateApplicationStatusAccepted(a, Status.ACCEPTED, "comment");
		Assert.isTrue(res.getStatus().equals(Status.ACCEPTED));
		super.authenticate(null);

	}

	@Test
	public void testfindAllByCustomer() {

		super.authenticate("customerUserName1");
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Collection<Application> res = new ArrayList<Application>(this.applicationService.findAllByCustomer());
		Application a = null;
		for (Application app : this.applicationService.findAll())
			if (app.getFixUpTask().getCustomer().getUserAccount().equals(userAccount))
				a = app;
		Assert.isTrue(res.contains(a));
		super.authenticate(null);
	}
}
