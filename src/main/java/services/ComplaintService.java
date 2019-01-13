
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ComplaintRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Complaint;
import domain.Customer;
import domain.HandyWorker;
import domain.Referee;
import domain.Report;

@Service
@Transactional
public class ComplaintService {

	@Autowired
	private ComplaintRepository	complaintRepository;

	@Autowired
	private RefereeService		refereeService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private HandyWorkerService	handyWorkerService;

	@Autowired
	private ReportService		reportService;


	public ComplaintService() {
		super();
	}

	public Complaint create() {

		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		//A customer call the method
		final Authority au = new Authority();
		au.setAuthority("CUSTOMER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		final Complaint c = new Complaint();
		return c;

	}
	public Complaint save(final Complaint complaint) {
		Assert.notNull(complaint);
		return this.complaintRepository.save(complaint);

	}

	public Collection<Complaint> findAll() {
		Collection<Complaint> res;
		res = this.complaintRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Complaint findOne(final int id) {
		Assert.isTrue(id != 0);
		Complaint res;
		res = this.complaintRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public void delete(final Complaint c) {
		Assert.notNull(c);
		final Collection<Customer> cus = this.customerService.findAll();
		for (final Customer cu : cus)
			if (cu.getComplaints().contains(c)) {
				cu.getComplaints().remove(c);
				this.customerService.save(cu);
			}
		final Collection<HandyWorker> han = this.handyWorkerService.findAll();
		for (final HandyWorker h : han)
			if (h.getComplaints().contains(c)) {
				h.getComplaints().remove(c);
				this.handyWorkerService.save(h);
			}

		final Collection<Report> rep = this.reportService.findAllReportsByComplaint(c);
		for (final Report r : rep)
			this.reportService.delete(r);
		this.complaintRepository.delete(c);
	}
	public Collection<Complaint> findAllByCustomer() {

		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		//A customer call the method
		final Authority au = new Authority();
		au.setAuthority("CUSTOMER");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		final Collection<Complaint> res = new ArrayList<Complaint>();

		for (final Complaint c : this.complaintRepository.findAll())
			if (c.getFixUpTask().getCustomer().getUserAccount().getId() == userAccount.getId())
				res.add(c);
		return res;

	}

	public Collection<Complaint> findAllUnasigned() {
		final Collection<Complaint> res = new ArrayList<Complaint>();
		for (final Complaint c : this.complaintRepository.findAllUnasigned()) {
			Assert.notNull(c);
			res.add(c);
		}
		return res;

	}

	public void assignComplaintReferee(final Complaint c) {

		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		final Authority au = new Authority();
		au.setAuthority("REFEREE");

		Assert.isTrue(userAccount.getAuthorities().contains(au));

		final Referee r = this.refereeService.findOne(userAccount.getId());

		final Collection<Complaint> res = new ArrayList<Complaint>();
		res.addAll(r.getComplaints());
		res.add(c);

	}

	public Complaint findByTicker(final String ticker) {
		Assert.notNull(ticker);
		Complaint res;
		res = this.complaintRepository.findByTicker(ticker);
		Assert.notNull(res);
		return res;
	}
}
