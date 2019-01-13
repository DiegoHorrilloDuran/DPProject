
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.PersonalRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PersonalRecordServiceTest extends AbstractTest {

	@Autowired
	private PersonalRecordService	personalRecordService;


	//Test
	@Test
	public void testCreatePersonalRecord() {
		PersonalRecord res = this.personalRecordService.create();
		Assert.notNull(res);
	}

	@Test
	public void testFindAllPersonalRecord() {
		Collection<PersonalRecord> res = this.personalRecordService.findAll();

		for (PersonalRecord p : res)
			System.out.println(p.getId() + " " + p.getFullName());
	}

	@Test
	public void testSavePersonalRecord() {
		PersonalRecord h = this.personalRecordService.findOne(983);
		h.setFullName("hola");
		this.personalRecordService.save(h);
		Assert.isTrue(this.personalRecordService.findAll().contains(h));
	}

	@Test
	public void testDeletePersonalRecord() {
		PersonalRecord p = this.personalRecordService.findOne(983);
		this.personalRecordService.delete(p);
		Collection<PersonalRecord> pp = this.personalRecordService.findAll();
		Assert.isTrue(!pp.contains(p));
	}
}
