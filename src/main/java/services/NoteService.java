
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.NoteRepository;
import security.Authority;
import security.LoginService;
import domain.Complaint;
import domain.Customer;
import domain.HandyWorker;
import domain.Note;
import domain.Referee;
import domain.Report;

@Service
@Transactional
public class NoteService {

	//Managed Repository-------------------------------------------------------

	@Autowired
	private NoteRepository		noteRepository;

	// Supporting services ----------------------------------------------------	

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private RefereeService		refereeService;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	public NoteService() {
		super();
	}

	//Simple CRUD methods-----------------------------------------------------

	public Note create(String creatorComment, Report report) {
		Note res = new Note();
		Collection<Authority> a = LoginService.getPrincipal().getAuthorities();

		if (a.contains("CUSTOMER")) {
			Collection<Complaint> c = this.customerService.findByPrincipal().getComplaints();
			Assert.isTrue(c.contains(report.complaint));
			res.setCreatorComment(creatorComment);
		}
		if (a.contains("REFEREE")) {
			Collection<Report> c = this.refereeService.findByPrincipal().getReports();
			Assert.isTrue(c.contains(report));
			Assert.isTrue(report.getFinalMode());
			res.setCreatorComment(creatorComment);
		}
		if (a.contains("HANDYWORKER")) {
			Collection<Complaint> c = this.handyWorkerService.findByPrincipal().getComplaints();
			Assert.isTrue(c.contains(report.complaint));
			res.setCreatorComment(creatorComment);
		}
		return res;
	}

	public Collection<Note> findAll() {
		Collection<Note> res;
		res = this.noteRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Note findOne(int id) {
		Assert.isTrue(id != 0);
		Note res;
		res = this.noteRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Note save(Note n) {
		Assert.notNull(n);
		return this.noteRepository.save(n);

	}
	public void delete(Note n) {
		Assert.notNull(n);
		Collection<HandyWorker> han = this.handyWorkerService.findAll();
		for (HandyWorker h : han)
			if (h.getNotes().contains(n)) {
				h.getNotes().remove(n);
				this.handyWorkerService.save(h);
			}
		Collection<Customer> cus = this.customerService.findAll();
		for (Customer c : cus)
			if (c.getNotes().contains(n)) {
				c.getNotes().remove(n);
				this.customerService.save(c);
			}
		Collection<Referee> ref = this.refereeService.findAll();
		for (Referee r : ref)
			if (r.getNotes().contains(n)) {
				r.getNotes().remove(n);
				this.refereeService.save(r);
			}
		this.noteRepository.delete(n);
	}

	//35.3
	public void writeComment(String comment, Note note) {
		Assert.notNull(note);
		Assert.notNull(comment);
		Collection<String> res = note.getComments();
		Collection<Authority> a = LoginService.getPrincipal().getAuthorities();

		if (a.contains("CUSTOMER")) {
			Collection<Complaint> c = this.customerService.findByPrincipal().getComplaints();
			Assert.isTrue(c.contains(note.report.complaint));
			res.add(comment);
		} else

		if (a.contains("REFEREE")) {
			Collection<Report> c = this.refereeService.findByPrincipal().getReports();
			Assert.isTrue(c.contains(note.report));
			Assert.isTrue(note.report.getFinalMode());
			res.add(comment);
		} else if (a.contains("HANDYWORKER")) {
			Collection<Complaint> c = this.handyWorkerService.findByPrincipal().getComplaints();
			Assert.isTrue(c.contains(note.report.complaint));
			res.add(comment);
		}
		note.setComments(res);
	}
	//36.5

}
