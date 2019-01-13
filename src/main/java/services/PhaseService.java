
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PhaseRepository;
import domain.HandyWorker;
import domain.Phase;
import domain.Workplan;

@Service
@Transactional
public class PhaseService {

	@Autowired
	private PhaseRepository	phaseRepository;
	@Autowired
	private WorkplanService	workplanService;


	public PhaseService() {

		super();

	}

	public Phase createPhase() {
		Phase res = new Phase();
		return res;
	}

	public Phase save(Phase p) {
		Assert.notNull(p);
		return this.phaseRepository.save(p);
	}

	public Phase findOne(int id) {
		Phase res;
		res = this.phaseRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Collection<Phase> findAll() {
		Collection<Phase> res;
		res = this.phaseRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public void delete(Phase phase) {
		Assert.notNull(phase);
		Collection<Workplan> wor = this.workplanService.findAll();
		for (Workplan w : wor)
			if (w.getPhases().contains(phase)) {
				w.getPhases().remove(phase);
				this.workplanService.save(w);
			}
		this.phaseRepository.delete(phase);
	}

	private Workplan getWorkplanByPhase(int phaseId) {
		return this.phaseRepository.getWorkplanByPhase(phaseId);
	}

	private HandyWorker getHandyWorkerByPhase(int phaseId) {
		return this.phaseRepository.getHandyWorkerByPhase(phaseId);
	}
}
