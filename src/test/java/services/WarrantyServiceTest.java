
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
import domain.Warranty;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class WarrantyServiceTest extends AbstractTest {

	@Autowired
	private WarrantyService		warrantyService;

	@Autowired
	private FixUpTaskService	fixUpTaskService;


	@Test
	public void testCreate() {
		Warranty res = this.warrantyService.create(this.fixUpTaskService.findOne(974));
		Assert.notNull(res);
	}

	@Test
	public void testSave() {
		Warranty w = this.warrantyService.findOne(1090);
		w.setTitle("33");
		this.warrantyService.save(w);
		Assert.isTrue(this.warrantyService.findAll().contains(w));
	}

	@Test
	public void testDelete() {
		Warranty w;
		Collection<Warranty> res;
		w = this.warrantyService.findOne(1090);
		this.warrantyService.delete(w);
		res = this.warrantyService.findAll();
		Assert.isTrue(!res.contains(w));
	}

}
