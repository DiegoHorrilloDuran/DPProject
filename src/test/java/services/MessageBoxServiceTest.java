
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
import domain.MessageBox;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MessageBoxServiceTest extends AbstractTest {

	@Autowired
	private MessageBoxService	messageBoxService;


	//Test			

	@Test
	public void testCreateMessageBox() {
		super.authenticate("handyWorkerUserName1");
		MessageBox res = this.messageBoxService.create();
		Assert.notNull(res);
		super.authenticate(null);

	}
	@Test
	public void testFindAllMessageBox() {

		Collection<MessageBox> res = this.messageBoxService.findAll();

		for (MessageBox m : res)
			System.out.println(m.getId() + " " + m.getBoxName());

		super.authenticate(null);

	}

	@Test
	public void testSaveMessageBox() {
		MessageBox h = this.messageBoxService.findOne(994);
		h.setBoxName("hola");
		this.messageBoxService.save(h);
		Assert.isTrue(this.messageBoxService.findAll().contains(h));
	}

	@Test
	public void testDeleteMessage() {
		MessageBox m = this.messageBoxService.findOne(999);
		this.messageBoxService.delete(m);
		Collection<MessageBox> mm = this.messageBoxService.findAll();
		Assert.isTrue(!mm.contains(m));
	}

}
