
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MiscellaneousRecordRepository;
import domain.Curriculum;
import domain.MiscellaneousRecord;

@Service
@Transactional
public class MiscellaneousRecordService {

	//Managed Repository-------------------------------------------------------

	@Autowired
	private MiscellaneousRecordRepository	miscellaneousRecordRepository;

	@Autowired
	private CurriculumService				curriculumService;


	// Supporting services ----------------------------------------------------	

	public MiscellaneousRecordService() {
		super();
	}

	//Simple CRUD methods-----------------------------------------------------

	public MiscellaneousRecord create() {
		MiscellaneousRecord res = new MiscellaneousRecord();
		return res;
	}

	public Collection<MiscellaneousRecord> findAll() {
		Collection<MiscellaneousRecord> res;
		res = this.miscellaneousRecordRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public MiscellaneousRecord findOne(int id) {
		Assert.isTrue(id != 0);
		MiscellaneousRecord res;
		res = this.miscellaneousRecordRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public MiscellaneousRecord save(MiscellaneousRecord m) {
		Assert.notNull(m);
		return this.miscellaneousRecordRepository.save(m);
	}

	public void delete(MiscellaneousRecord m) {
		Assert.notNull(m);
		Collection<Curriculum> cur = this.curriculumService.findAll();
		for (Curriculum c : cur)
			if (c.getMiscellaneousRecords().contains(m)) {
				c.getMiscellaneousRecords().remove(m);
				this.curriculumService.save(c);
			}
		this.miscellaneousRecordRepository.delete(m);
	}
}
