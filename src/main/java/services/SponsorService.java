
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Message;
import domain.MessageBox;
import domain.SocialProfile;
import domain.Sponsor;
import domain.Tutorial;

@Service
@Transactional
public class SponsorService {

	@Autowired
	private SponsorRepository	sponsorRepository;


	public SponsorService() {
		super();
	}

	//CREATE
	public Sponsor createSponsor() {
		Collection<SocialProfile> socialProfiles = new ArrayList<SocialProfile>();
		Collection<MessageBox> messageBoxes = new ArrayList<MessageBox>();
		Collection<Message> messagesSent = new ArrayList<Message>();
		Collection<Message> messagesRecipient = new ArrayList<Message>();
		Collection<Tutorial> tutorials = new ArrayList<Tutorial>();
		UserAccount userAccount = new UserAccount();
		Authority aut = new Authority();
		aut.setAuthority("SPONSOR");
		Collection<Authority> authorities = new HashSet<Authority>();
		authorities.add(aut);
		userAccount.setAuthorities(authorities);
		Sponsor res = new Sponsor();
		res.setSocialProfiles(socialProfiles);
		res.setMessageBoxes(messageBoxes);
		res.setMessagesSent(messagesSent);
		res.setMessagesRecipient(messagesRecipient);
		res.setTutorials(tutorials);
		return res;
	}

	//SAVE
	public Sponsor save(Sponsor sponsor) {
		Assert.notNull(sponsor);
		Sponsor yo = this.findByPrincipal();
		if (sponsor.getId() != 0)
			Assert.isTrue(yo.getId() == sponsor.getId());
		Sponsor res = this.sponsorRepository.save(sponsor);
		return res;
	}

	public Sponsor findByPrincipal() {
		Sponsor res;
		UserAccount ua = LoginService.getPrincipal();
		Assert.notNull(ua);
		res = this.sponsorRepository.findByUserAccountId(ua.getId());
		return res;
	}

	//FINDONE
	public Sponsor findOne(int id) {
		Sponsor res;
		res = this.sponsorRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	//FINDALL
	public Collection<Sponsor> findAll() {
		Collection<Sponsor> res;
		res = this.sponsorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public void delete(Sponsor s) {
		Assert.notNull(s);
		this.sponsorRepository.delete(s);
	}

	public Sponsor create() {
		return new Sponsor();
	}

}
