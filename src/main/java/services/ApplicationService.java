
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Status;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository	applicationRepository;


	public ApplicationService() {
		super();
	}

	public Application create(HandyWorker handyWorker, FixUpTask fixUpTask) {
		Assert.notNull(handyWorker);
		Assert.notNull(fixUpTask);
		Application a = new Application();
		a.setHandyWorker(handyWorker);
		a.setFixUpTask(fixUpTask);
		return a;

	}

	public Collection<Application> findAll() {
		Collection<Application> res;
		res = this.applicationRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Application save(Application application) {
		Assert.notNull(application);
		return this.applicationRepository.save(application);
	}

	public Application findOne(int id) {
		Assert.isTrue(id != 0);
		Application res;
		res = this.applicationRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public void delete(Application application) {
		Assert.notNull(application);
		this.applicationRepository.delete(application);

	}

	public Application customerUpdateApplicationStatusRejected(Application application, Status status, String comment) {

		Assert.notNull(application);
		Assert.notNull(status);
		//Application must have pending as status
		Assert.isTrue(application.getStatus().equals(Status.PENDING));
		//new status must be rejected
		Assert.isTrue(status.equals(Status.REJECTED));

		//Principal must be the customer that published the fixUpTask
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();

		Assert.isTrue(application.getFixUpTask().getCustomer().getUserAccount().equals(userAccount));

		application.setStatus(status);
		application.getComments().add(comment);
		return this.applicationRepository.save(application);

	}

	public Application customerUpdateApplicationStatusAccepted(Application application, Status status, String comment) {

		Assert.notNull(application);
		Assert.notNull(status);
		//Application must have pending as status
		Assert.isTrue(application.getStatus().equals(Status.PENDING));
		//new status must be accepted
		Assert.isTrue(status.equals(Status.ACCEPTED));

		//Principal must be the customer that published the fixUpTask
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();

		Assert.isTrue(application.getFixUpTask().getCustomer().getUserAccount().equals(userAccount));

		application.setStatus(status);
		application.getComments().add(comment);
		return this.applicationRepository.save(application);

	}

	public Collection<Application> findAllByCustomer() {

		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		//A customer call the method
		Authority au = new Authority();
		au.setAuthority("CUSTOMER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		Collection<Application> res = new ArrayList<Application>();

		for (Application a : this.applicationRepository.findAll())
			if (a.getFixUpTask().getCustomer().getUserAccount().getId() == userAccount.getId())
				res.add(a);
		return res;
	}
	public Collection<Application> findAllByHandyWorker() {

		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();

		Collection<Application> res = new ArrayList<Application>();

		for (Application a : this.applicationRepository.findAll())
			if (a.getHandyWorker().getUserAccount().getId() == userAccount.getId())
				res.add(a);
		return res;

	}

}
