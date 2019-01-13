
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EducationRecordRepository;
import domain.Curriculum;
import domain.EducationRecord;

@Service
@Transactional
public class EducationRecordService {

	// Managed repository -------------------------------------
	@Autowired
	private EducationRecordRepository	educationRecordRepository;

	@Autowired
	private CurriculumService			curriculumService;


	public EducationRecordService() {
		super();
	}

	// Supporting services ------------------------------------

	// CRUD methods -------------------------------------------

	public EducationRecord create() {
		EducationRecord res = new EducationRecord();
		return res;
	}

	public Collection<EducationRecord> findAll() {
		Collection<EducationRecord> res;
		res = this.educationRecordRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public EducationRecord findOne(int id) {
		Assert.isTrue(id != 0);
		EducationRecord res;
		res = this.educationRecordRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public EducationRecord save(EducationRecord e) {
		Assert.notNull(e);
		return this.educationRecordRepository.save(e);
	}

	public void delete(EducationRecord e) {
		Assert.notNull(e);
		Collection<Curriculum> cur = this.curriculumService.findAll();
		for (Curriculum c : cur)
			if (c.getEducationRecords().contains(e)) {
				c.getEducationRecords().remove(e);
				this.curriculumService.save(c);
			}
		this.educationRecordRepository.delete(e);
	}

}
