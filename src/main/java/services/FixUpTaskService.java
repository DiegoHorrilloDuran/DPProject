
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FixUpTaskRepository;
import domain.Application;
import domain.Complaint;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.SearchResult;
import domain.Warranty;
import domain.Workplan;

@Service
@Transactional
public class FixUpTaskService {

	// Managed repository -------------------------------------
	@Autowired
	private FixUpTaskRepository	fixUpTaskRepository;

	@Autowired
	private ComplaintService	complaintService;

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private SearchResultService	searchResultService;

	@Autowired
	private WarrantyService		warrantyService;

	@Autowired
	private WorkplanService		workplanService;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	// Supporting services ------------------------------------

	public FixUpTaskService() {
		super();
	}

	// CRUD methods -------------------------------------------

	public FixUpTask create() {
		FixUpTask res = new FixUpTask();
		return res;
	}

	public Collection<FixUpTask> findAll() {
		Collection<FixUpTask> res;
		res = this.fixUpTaskRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public FixUpTask findOne(int id) {
		Assert.isTrue(id != 0);
		FixUpTask res;
		res = this.fixUpTaskRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public FixUpTask save(FixUpTask f) {
		Assert.notNull(f);
		return this.fixUpTaskRepository.save(f);
	}

	public void delete(FixUpTask f) {
		Assert.notNull(f);
		Collection<Complaint> com = this.complaintService.findAll();
		for (Complaint c : com)
			if (c.getFixUpTask().equals(f))
				this.complaintService.delete(c);
		Collection<Application> app = this.applicationService.findAll();
		for (Application a : app)
			if (a.getFixUpTask().equals(f))
				this.applicationService.delete(a);
		Collection<SearchResult> sea = this.searchResultService.findAll();
		for (SearchResult s : sea)
			if (s.getFixUpTasks().contains(f)) {
				s.getFixUpTasks().remove(f);
				this.searchResultService.save(s);
			}
		Collection<Warranty> war = this.warrantyService.findAll();
		for (Warranty w : war)
			if (w.getFixUpTask().equals(f))
				this.warrantyService.delete(w);
		Collection<Workplan> wor = this.workplanService.findAll();
		Collection<HandyWorker> han = this.handyWorkerService.findAll();
		for (Workplan wo : wor)
			if (wo.getFixUpTask().equals(f)) {
				for (HandyWorker h : han)
					if (h.getWorkplans().contains(wo)) {
						h.getWorkplans().remove(wo);
						this.handyWorkerService.save(h);
					}
				this.workplanService.delete(wo);
			}
		this.fixUpTaskRepository.delete(f);
	}

}
