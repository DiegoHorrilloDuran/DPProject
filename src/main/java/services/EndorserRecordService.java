
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EndorserRecordRepository;
import domain.Curriculum;
import domain.EndorserRecord;

@Service
@Transactional
public class EndorserRecordService {

	// Managed repository -------------------------------------
	@Autowired
	private EndorserRecordRepository	endorserRecordRepository;

	@Autowired
	private CurriculumService			curriculumService;


	public EndorserRecordService() {
		super();
	}

	// Supporting services ------------------------------------

	// CRUD methods -------------------------------------------

	public EndorserRecord create() {
		EndorserRecord res = new EndorserRecord();
		return res;
	}

	public Collection<EndorserRecord> findAll() {
		Collection<EndorserRecord> res;
		res = this.endorserRecordRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public EndorserRecord findOne(int id) {
		Assert.isTrue(id != 0);
		EndorserRecord res;
		res = this.endorserRecordRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public EndorserRecord save(EndorserRecord e) {
		Assert.notNull(e);
		return this.endorserRecordRepository.save(e);
	}

	public void delete(EndorserRecord e) {
		Assert.notNull(e);
		Collection<Curriculum> cur = this.curriculumService.findAll();
		for (Curriculum c : cur)
			if (c.getEndorserRecords().contains(e)) {
				c.getEndorserRecords().remove(e);
				this.curriculumService.save(c);
			}
		this.endorserRecordRepository.delete(e);
	}

}
