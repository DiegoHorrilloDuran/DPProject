
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorshipsRepository;
import domain.Sponsor;
import domain.Sponsorships;

@Service
@Transactional
public class SponsorshipsService {

	@Autowired
	private SponsorshipsRepository	sponsorshipsRepository;
	@Autowired
	private SponsorService			sponsorService;


	public SponsorshipsService() {
		super();
	}

	//CREATE
	public Sponsorships create() {
		Sponsorships res = new Sponsorships();
		res.setSponsor(this.sponsorService.findByPrincipal());
		return res;
	}

	//SAVE
	public Sponsorships save(Sponsorships sponsorships) {
		Assert.notNull(sponsorships);
		return this.sponsorshipsRepository.save(sponsorships);
	}

	public Sponsorships saveFromTutorial(Sponsorships sp) {
		Assert.notNull(sp);
		Sponsorships asd = this.sponsorshipsRepository.save(sp);
		return asd;
	}

	//DELETE
	public void delete(Sponsorships sponsorships) {
		Assert.notNull(sponsorships);
		Collection<Sponsor> spo = this.sponsorService.findAll();
		for (Sponsor s : spo)
			if (s.getSponsorships().equals(sponsorships))
				this.sponsorService.delete(s);
		this.sponsorshipsRepository.delete(sponsorships);
	}

	//FINDONE
	public Sponsorships findOne(int id) {
		Sponsorships res;
		res = this.sponsorshipsRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Collection<Sponsorships> findAll() {
		Collection<Sponsorships> res = this.sponsorshipsRepository.findAll();
		Assert.notNull(res);
		return res;
	}
}
