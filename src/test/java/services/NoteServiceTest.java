
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
import domain.Complaint;
import domain.Note;
import domain.Report;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class NoteServiceTest extends AbstractTest {

	@Autowired
	private NoteService			noteService;

	@Autowired
	private ReportService		reportService;

	@Autowired
	private ComplaintService	complaintService;


	//Test
	@Test
	public void testCreateNote() {
		super.authenticate("customerUserName1");
		Complaint c = this.complaintService.create();
		Report r = this.reportService.create(c);
		Note res = this.noteService.create("mola", r);
		Assert.notNull(res);
		super.authenticate(null);

	}
	@Test
	public void testFindAllNote() {
		Collection<Note> res = this.noteService.findAll();

		for (Note n : res)
			System.out.println(n.getId() + " " + n.getCreatorComment());
	}

	@Test
	public void testSaveNote() {
		Note h = this.noteService.findOne(1103);
		h.setCreatorComment("hola");
		this.noteService.save(h);
		Assert.isTrue(this.noteService.findAll().contains(h));
	}

	@Test
	public void testDeleteNote() {
		Note n = this.noteService.findOne(1103);
		this.noteService.delete(n);
		Collection<Note> nn = this.noteService.findAll();
		Assert.isTrue(!nn.contains(n));
	}
}
