
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.EndorserRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EndorserRecordServiceTest {

	// Service under test

	@Autowired
	private EndorserRecordService	endorserRecordService;


	// test

	@Test
	public void testCreateEndorserRecord() {
		EndorserRecord res = this.endorserRecordService.create();
		Assert.notNull(res);
	}

	@Test
	public void testFindAllEndorserRecords() {
		Collection<EndorserRecord> endorserRecords = this.endorserRecordService.findAll();
		for (EndorserRecord er : endorserRecords)
			System.out.println(er.getId() + " " + er.getEndorserFullName());
	}

	@Test
	public void testSaveEndorserRecord() {
		EndorserRecord er, saved;
		Collection<EndorserRecord> endorserRecords;

		er = this.endorserRecordService.findOne(972);
		er.setEndorserFullName("X");
		er.setEmail("x@x.com");
		er.setPhoneNumber("666666666");
		er.setLinkedInProfile("https://www.x.com");

		saved = this.endorserRecordService.save(er);
		endorserRecords = this.endorserRecordService.findAll();

		Assert.isTrue(endorserRecords.contains(saved));
	}
	@Test
	public void testDeleteEndorserRecord() {
		EndorserRecord d;
		Collection<EndorserRecord> endorserRecords;

		d = this.endorserRecordService.findOne(972);
		this.endorserRecordService.delete(d);
		endorserRecords = this.endorserRecordService.findAll();

		Assert.isTrue(!endorserRecords.contains(d));
	}

}
