
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
import domain.MiscellaneousRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MiscellaneousRecordServiceTest extends AbstractTest {

	@Autowired
	private MiscellaneousRecordService	miscellaneousRecordService;


	//Test
	@Test
	public void testCreateMiscellaneousRecord() {
		MiscellaneousRecord res = this.miscellaneousRecordService.create();
		Assert.notNull(res);
		super.authenticate(null);

	}
	@Test
	public void testFindAllMiscellaneousRecord() {
		Collection<MiscellaneousRecord> res = this.miscellaneousRecordService.findAll();
		for (MiscellaneousRecord m : res)
			System.out.println(m.getId() + " " + m.getTitle());

		super.authenticate(null);

	}

	@Test
	public void testSaveMiscellaneousRecord() {
		MiscellaneousRecord h = this.miscellaneousRecordService.findOne(982);
		h.setTitle("hola");
		this.miscellaneousRecordService.save(h);
		Assert.isTrue(this.miscellaneousRecordService.findAll().contains(h));

	}
	@Test
	public void testDeleteMiscellaneousRecord() {
		MiscellaneousRecord m = this.miscellaneousRecordService.findOne(982);
		this.miscellaneousRecordService.delete(m);
		Collection<MiscellaneousRecord> mm = this.miscellaneousRecordService.findAll();
		Assert.isTrue(!mm.contains(m));
	}

}
