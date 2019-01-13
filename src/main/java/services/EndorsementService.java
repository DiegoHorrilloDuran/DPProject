
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EndorsementRepository;
import domain.Admin;
import domain.Endorsement;

@Service
@Transactional
public class EndorsementService {

	// Managed repository -------------------------------------
	@Autowired
	private EndorsementRepository	endorsementRepository;

	// Supporting services ------------------------------------

	@Autowired
	private AdminService			adminService;


	// CRUD methods -------------------------------------------

	public Endorsement create() {
		Endorsement res = new Endorsement();
		return res;
	}

	public Collection<Endorsement> findAll() {
		Collection<Endorsement> res;
		res = this.endorsementRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Endorsement findOne(int id) {
		Assert.isTrue(id != 0);
		Endorsement res;
		res = this.endorsementRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Endorsement save(Endorsement e) {
		Assert.notNull(e);
		this.endorsementRepository.save(e);
		return e;
	}

	public void delete(Endorsement e) {
		Assert.notNull(e);
		Collection<Admin> adm = this.adminService.findAll();
		for (Admin a : adm)
			if (a.getEndorsements().contains(e)) {
				a.getEndorsements().remove(e);
				this.adminService.save(a);
			}
		this.endorsementRepository.delete(e);
	}

}
