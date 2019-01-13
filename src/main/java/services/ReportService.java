
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import domain.Complaint;
import domain.Note;
import domain.Referee;
import domain.Report;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository	reportRepository;
	@Autowired
	private RefereeService		refereeService;
	@Autowired
	private NoteService			noteService;


	public ReportService() {
		super();
	}

	public Report create(Complaint complaint) {
		Collection<String> attachments = new ArrayList<String>();
		Collection<Note> notes = new ArrayList<Note>();
		Date hoy = new Date(System.currentTimeMillis() - 1000);
		Report res = new Report();
		res.setAttachments(attachments);
		res.setNotes(notes);
		res.setDate(hoy);
		res.setFinalMode(false);
		res.setComplaint(complaint);
		return res;
	}
	public Report save(Report report) {
		Assert.notNull(report);
		return this.reportRepository.save(report);
	}
	public void setFinalMode(Report report) {
		Assert.notNull(report);
		Referee yo = this.refereeService.findByPrincipal();
		Assert.isTrue(yo.getReports().contains(report) && !report.getFinalMode());
		report.setFinalMode(true);
		this.reportRepository.save(report);
	}

	public Report findOne(int id) {
		Report res;
		res = this.reportRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public void delete(Report report) {
		Assert.notNull(report);
		Collection<Referee> ref = this.refereeService.findAll();
		for (Referee r : ref)
			if (r.getReports().contains(report)) {
				r.getReports().remove(report);
				this.refereeService.save(r);
			}
		Collection<Note> not = this.noteService.findAll();
		for (Note n : not)
			if (n.report.equals(report))
				this.noteService.delete(n);
		this.reportRepository.delete(report);
	}
	public Collection<Report> getAllReportsByHandyWorker(int idHandyWorker) {
		return this.reportRepository.getAllReportsByHandyWorker(idHandyWorker);
	}

	public Collection<Report> findAll() {
		Collection<Report> res;
		res = this.reportRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Collection<Report> findAllReportsByComplaint(Complaint complaint) {
		Assert.notNull(complaint);
		Collection<Report> res;
		res = this.reportRepository.findAllReportsByComplaint(complaint);
		Assert.notNull(res);
		return res;
	}
}
