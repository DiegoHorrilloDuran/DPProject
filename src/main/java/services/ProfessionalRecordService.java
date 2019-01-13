
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ProfessionalRecordRepository;
import domain.Curriculum;
import domain.HandyWorker;
import domain.ProfessionalRecord;

@Service
@Transactional
public class ProfessionalRecordService {

	@Autowired
	private ProfessionalRecordRepository	professionalRecordRepository;
	@Autowired
	private CurriculumService				curriculumService;


	public ProfessionalRecordService() {
		super();
	}

	public ProfessionalRecord create() {
		ProfessionalRecord res = new ProfessionalRecord();
		return res;
	}

	/*
	 * public ProfessionalRecord save(ProfessionalRecord professionalRecord,Curriculum curriculum) {
	 * Assert.notNull(professionalRecord);
	 * Date hoy = new Date();
	 * Assert.isTrue(professionalRecord.getStartDate().before(hoy));
	 * 
	 * ProfessionalRecord prDef = this.professionalRecordRepository.save(professionalRecord);
	 * if (professionalRecord.getId() == 0) {
	 * curriculum.getProfessionalRecords().add(prDef);
	 * this.curriculumService.save(curriculum);
	 * }
	 * return prDef;
	 * }
	 */
	public ProfessionalRecord save(ProfessionalRecord e) {
		Assert.notNull(e);
		return this.professionalRecordRepository.save(e);
	}

	public ProfessionalRecord findOne(int id) {
		ProfessionalRecord res;
		res = this.professionalRecordRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public void delete(ProfessionalRecord p) {
		Assert.notNull(p);
		Collection<Curriculum> cur = this.curriculumService.findAll();
		for (Curriculum c : cur)
			if (c.getProfessionalRecords().contains(p)) {
				c.getProfessionalRecords().remove(p);
				this.curriculumService.save(c);
			}
		this.professionalRecordRepository.delete(p);
	}

	private HandyWorker getHandyWorkerByRecordRepository(int prId) {
		return this.professionalRecordRepository.getHandyWorkerByProfessionalRecord(prId);
	}

	public Collection<ProfessionalRecord> findAll() {
		Collection<ProfessionalRecord> res;
		res = this.professionalRecordRepository.findAll();
		Assert.notNull(res);
		return res;
	}
}
