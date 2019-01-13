
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PersonalRecordRepository;
import domain.Curriculum;
import domain.PersonalRecord;

@Service
@Transactional
public class PersonalRecordService {

	//Managed Repository-------------------------------------------------------

	@Autowired
	private PersonalRecordRepository	personalRecordRepository;

	@Autowired
	private CurriculumService			curriculumService;


	// Supporting services ----------------------------------------------------	

	public PersonalRecordService() {
		super();
	}

	//Simple CRUD methods-----------------------------------------------------

	public PersonalRecord create() {
		PersonalRecord res = new PersonalRecord();
		return res;
	}

	public Collection<PersonalRecord> findAll() {
		Collection<PersonalRecord> res;
		res = this.personalRecordRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public PersonalRecord findOne(int id) {
		Assert.isTrue(id != 0);
		PersonalRecord res;
		res = this.personalRecordRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public PersonalRecord save(PersonalRecord p) {
		Assert.notNull(p);
		return this.personalRecordRepository.save(p);
	}

	public void delete(PersonalRecord p) {
		Assert.notNull(p);
		Collection<Curriculum> cur = this.curriculumService.findAll();
		for (Curriculum c : cur)
			if (c.getPersonalRecords().contains(p)) {
				c.getPersonalRecords().remove(p);
				this.curriculumService.save(c);
			}
		this.personalRecordRepository.delete(p);
	}

}
