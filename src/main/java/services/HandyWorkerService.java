
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.HandyWorkerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.Curriculum;
import domain.Endorsement;
import domain.HandyWorker;

@Service
@Transactional
public class HandyWorkerService {

	//Managed Repository-------------------------------------------------------

	@Autowired
	private HandyWorkerRepository	handyWorkerRepository;

	@Autowired
	private ApplicationService		applicationService;

	@Autowired
	private CurriculumService		curriculumService;

	@Autowired
	private EndorsementService		endorsementService;


	// Supporting services ----------------------------------------------------	

	public HandyWorkerService() {
		super();
	}

	//Simple CRUD methods-----------------------------------------------------

	public void delete(HandyWorker handyWorker) {
		Assert.notNull(handyWorker);
		Assert.isTrue(handyWorker.getId() != 0);
		Collection<Application> app = this.applicationService.findAll();
		for (Application a : app)
			if (a.getHandyWorker().equals(handyWorker))
				this.applicationService.delete(a);
		Collection<Curriculum> cum = this.curriculumService.findAll();
		for (Curriculum c : cum)
			if (c.getHandyWorker().equals(handyWorker))
				this.curriculumService.delete(c);
		Collection<Endorsement> end = this.endorsementService.findAll();
		for (Endorsement e : end) {
			if (e.getHandyWorkerRecipient().equals(handyWorker)) {
				e.setHandyWorkerRecipient(null);
				this.endorsementService.save(e);
			}
			if (e.getHandyWorkerSender().equals(handyWorker)) {
				e.setHandyWorkerSender(null);
				this.endorsementService.save(e);
			}
		}
		this.handyWorkerRepository.delete(handyWorker);

	}
	public HandyWorker save(HandyWorker handyWorker) {
		Assert.notNull(handyWorker);
		return this.handyWorkerRepository.save(handyWorker);

	}

	public HandyWorker findOne(int id) {
		Assert.isTrue(id != 0);
		HandyWorker res;
		res = this.handyWorkerRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Collection<HandyWorker> findAll() {
		Collection<HandyWorker> res;
		res = this.handyWorkerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public HandyWorker findByPrincipal() {
		UserAccount userAccount;
		HandyWorker handyWorker;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount, "User Account is null.");
		handyWorker = this.handyWorkerRepository.findByPrincipal(userAccount.getId());
		return handyWorker;
	}

	public HandyWorker create() {
		return new HandyWorker();
	}
}
