
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
import domain.EducationRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EducationRecordServiceTest extends AbstractTest {

	// Service under test
	@Autowired
	private EducationRecordService	educationRecordService;


	// test

	@Test
	public void testCreateEducationRecord() {
		EducationRecord res = this.educationRecordService.create();
		Assert.notNull(res);
	}

	@Test
	public void testFindAllEducationRecords() {
		Collection<EducationRecord> educationRecords = this.educationRecordService.findAll();
		for (EducationRecord er : educationRecords)
			System.out.println(er.getId() + " " + er.getDiplomaTitle());
	}

	@Test
	public void testSaveEducationRecord() {
		EducationRecord er, saved;
		Collection<EducationRecord> educationRecords;

		er = this.educationRecordService.create();
		er.setDiplomaTitle("X");
		er.setInstitution("X");
		er.setStartDate(LocalDate.now().toDate());

		saved = this.educationRecordService.save(er);
		educationRecords = this.educationRecordService.findAll();

		Assert.isTrue(educationRecords.contains(saved));
	}

	@Test
	public void testDeleteEducationRecord() {
		EducationRecord d;
		Collection<EducationRecord> educationRecords;

		d = this.educationRecordService.findOne(968);
		this.educationRecordService.delete(d);
		educationRecords = this.educationRecordService.findAll();

		Assert.isTrue(!educationRecords.contains(d));
	}

}
