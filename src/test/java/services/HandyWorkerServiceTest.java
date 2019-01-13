
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
import domain.HandyWorker;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class HandyWorkerServiceTest extends AbstractTest {

	@Autowired
	private HandyWorkerService	handyWorkerService;


	//Test
	/*
	 * @Test
	 * public void testCreateHandyWorker(){
	 * HandyWorker res= new HandyWorker();
	 * Assert.notNull(res);
	 * super.authenticate(null);
	 * 
	 * 
	 * }
	 */
	@Test
	public void testFindAllHandyWorker() {
		super.authenticate("HandyWorkerUserName2");
		Collection<HandyWorker> res = this.handyWorkerService.findAll();

		for (HandyWorker h : res)
			System.out.println(h.getId() + " " + h.getName());
		Assert.notNull(res);
		super.authenticate(null);

	}

	@Test
	public void testCreateHandyWorker() {
		HandyWorker res = new HandyWorker();
		Assert.notNull(res);
		super.authenticate(null);

	}

	@Test
	public void testSaveHandyWorker() {
		HandyWorker h = this.handyWorkerService.findOne(980);
		String h2 = h.getMake();
		h.setMake("hola");
		HandyWorker saved = this.handyWorkerService.save(h);
		Collection<HandyWorker> hs = this.handyWorkerService.findAll();
		Assert.isTrue(hs.contains(saved));
	}

	@Test
	public void testDeleteHandyWorker() {
		HandyWorker h = this.handyWorkerService.findOne(980);
		this.handyWorkerService.delete(h);
		Collection<HandyWorker> hh = this.handyWorkerService.findAll();
		Assert.isTrue(!hh.contains(h));
	}

}
