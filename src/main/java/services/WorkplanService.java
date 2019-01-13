
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WorkplanRepository;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Phase;
import domain.Workplan;

@Service
@Transactional
public class WorkplanService {

	@Autowired
	private WorkplanRepository	workplanRepository;
	@Autowired
	private HandyWorkerService	handyWorkerService;


	public WorkplanService() {
		super();
	}

	//CREATE
	public Workplan create(FixUpTask fixUpTask) {
		Workplan res = new Workplan();
		Collection<Phase> phases = new ArrayList<Phase>();
		res.setFixUpTask(fixUpTask);
		res.setPhases(phases);
		return res;
	}

	//SAVE
	public Workplan save(Workplan workplan) {
		Assert.notNull(workplan);
		return this.workplanRepository.save(workplan);
	}

	//FINDONE
	public Workplan findOne(int id) {
		Workplan res;
		res = this.workplanRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	//FINDALL
	public Collection<Workplan> findAll() {
		Collection<Workplan> res;
		res = this.workplanRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Workplan saveFromPhase(Workplan workplan) {
		Assert.notNull(workplan);
		Workplan res = this.workplanRepository.save(workplan);
		return res;
	}

	public void delete(Workplan w) {
		Assert.notNull(w);
		Collection<HandyWorker> han = this.handyWorkerService.findAll();
		for (HandyWorker h : han)
			if (h.getWorkplans().contains(w)) {
				Collection<Workplan> work = h.getWorkplans();
				work.remove(w);
				h.setWorkplans(work);
				this.handyWorkerService.save(h);
			}
		this.workplanRepository.delete(w);
	}
}
