
package services;

import java.util.Collection;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.ProfessionalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class ProfessionalRecordServiceTest extends AbstractTest {

	// Service under test
	@Autowired
	private ProfessionalRecordService	professionalRecordService;


	// test

	@Test
	public void testCreate() {
		ProfessionalRecord res = this.professionalRecordService.create();
		Assert.notNull(res);
	}

	@Test
	public void testFindAll() {
		Collection<ProfessionalRecord> professionalRecord = this.professionalRecordService.findAll();
		for (ProfessionalRecord er : professionalRecord)
			System.out.println(er.getId() + " " + er.getCompanyName());
	}

	@Test
	public void testSave() {
		ProfessionalRecord er, saved;
		Collection<ProfessionalRecord> professionalRecords;

		/*
		 * Curriculum c
		 * Collection<Currriculum> curriculum
		 * c = this.curriculumService.findOne()
		 */
		er = this.professionalRecordService.findOne(984);
		er.setCompanyName("X");
		er.setRole("X");
		er.setStartDate(LocalDate.now().toDate());

		saved = this.professionalRecordService.save(er);
		professionalRecords = this.professionalRecordService.findAll();

		Assert.isTrue(professionalRecords.contains(saved));
	}

	@Test
	public void testDelete() {
		ProfessionalRecord d;
		Collection<ProfessionalRecord> professionalRecords;

		d = this.professionalRecordService.findOne(984);
		this.professionalRecordService.delete(d);
		professionalRecords = this.professionalRecordService.findAll();

		Assert.isTrue(!professionalRecords.contains(d));
	}

}
