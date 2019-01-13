
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
import domain.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MessageServiceTest extends AbstractTest {

	@Autowired
	private MessageService	messageService;


	//Test
	@Test
	public void testCreateMessage() {
		super.authenticate("HandyWorkerUserName2");
		Message res = this.messageService.create();
		Assert.notNull(res);
		super.authenticate(null);

	}

	@Test
	public void testFindAllMessage() {
		Collection<Message> res = this.messageService.findAll();

		for (Message m : res)
			System.out.println(m.getId() + " " + m.getSubject());
		Assert.notNull(res);
	}

	/*
	 * 
	 * 
	 * @Test
	 * public void testFindAllBySenderMessage(){
	 * super.authenticate("HandyWorkerUserName2");
	 * Collection<Message> res = this.messageService.findAllByActorSender();
	 * 
	 * for(Message m : res){
	 * System.out.println(m.getId()+" "+m.getSubject());
	 * }
	 * Assert.notNull(res);
	 * super.authenticate(null);
	 * 
	 * }
	 * 
	 * @Test
	 * public void testSaveMessage(){
	 * Message h = messageService.findOne(33);
	 * String h2 = messageService.findOne(33).getSubject();
	 * h.setSubject("hola");
	 * messageService.save(h);
	 * String h3=messageService.findOne(33).getSubject();
	 * Assert.isTrue(!(h3==h2));
	 * }
	 * 
	 * 
	 * @Test
	 * public void testDeleteMessage(){
	 * super.authenticate("handyworker");
	 * Message m = messageService.findOne(33);
	 * messageService.delete(m);
	 * Collection<Message> mm = messageService.findAll();
	 * Assert.isTrue(!mm.contains(m));
	 * super.authenticate(null);
	 * }
	 */

}
