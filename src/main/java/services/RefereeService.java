
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Complaint;
import domain.Message;
import domain.MessageBox;
import domain.Note;
import domain.Referee;
import domain.Report;
import domain.SocialProfile;
import domain.Tutorial;

@Service
@Transactional
public class RefereeService {

	@Autowired
	private RefereeRepository	refereeRepository;

	@Autowired
	private ComplaintService	complaintService;


	public RefereeService() {
		super();
	}

	public Referee create() {
		Collection<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();
		Collection<MessageBox> messageBoxes = new ArrayList<MessageBox>();
		Collection<Message> messagesSent = new ArrayList<Message>();
		Collection<Message> messagesRecipient = new ArrayList<Message>();
		Collection<Tutorial> tutorials = new ArrayList<Tutorial>();
		Collection<Report> reports = new ArrayList<Report>();
		Collection<Note> notes = new ArrayList<Note>();
		UserAccount userAccount = new UserAccount();
		Authority aut = new Authority();
		aut.setAuthority("REFEREE");
		Collection<Authority> authorities = new HashSet<Authority>();
		authorities.add(aut);
		userAccount.setAuthorities(authorities);
		Referee res = new Referee();
		res.setSocialProfiles(socialProfiles);
		//res.setUserAccounts(userAccounts);
		res.setMessageBoxes(messageBoxes);
		res.setMessagesSent(messagesSent);
		res.setMessagesRecipient(messagesRecipient);
		res.setTutorials(tutorials);
		res.setReports(reports);
		res.setNotes(notes);
		res.setUserAccount(userAccount);
		return res;
	}

	public Referee save(Referee referee) {
		Assert.notNull(referee);
		return this.refereeRepository.save(referee);
	}

	public Referee findOne(int id) {
		Referee res;
		res = this.refereeRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Referee findByPrincipal() {
		UserAccount userAccount;
		Referee r;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount, "User Account is null.");
		r = this.refereeRepository.findByPrincipal(userAccount.getId());
		return r;
	}

	public Collection<Referee> findAll() {
		Collection<Referee> res;
		res = this.refereeRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public void delete(Referee r) {
		Assert.notNull(r);
		Collection<Complaint> com = this.complaintService.findAll();
		for (Complaint c : com)
			if (c.getReferee().equals(r))
				c.setReferee(null);
		this.refereeRepository.delete(r);

	}
}
