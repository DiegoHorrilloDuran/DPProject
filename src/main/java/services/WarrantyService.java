
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WarrantyRepository;
import domain.Admin;
import domain.FixUpTask;
import domain.Warranty;

@Service
@Transactional
public class WarrantyService {

	@Autowired
	private WarrantyRepository	warrantyRepository;

	@Autowired
	private AdminService		adminService;


	public WarrantyService() {
		super();
	}

	//CREATE
	public Warranty create(FixUpTask fixUpTask) {
		Warranty res = new Warranty();
		res.setFixUpTask(fixUpTask);
		res.setFinalMode(false);
		return res;
	}

	//SAVE
	public Warranty save(Warranty warranty) {
		Assert.notNull(warranty);
		return this.warrantyRepository.save(warranty);
	}
	public void setFinalMode(Warranty warranty) {
		Assert.notNull(warranty);
		warranty.setFinalMode(true);
		this.warrantyRepository.save(warranty);
	}

	//DELETE
	public void delete(Warranty warranty) {
		Assert.notNull(warranty);
		Collection<Admin> adm = this.adminService.findAll();
		for (Admin a : adm)
			if (a.getCataloguesOfWarranties().contains(warranty)) {
				a.getCataloguesOfWarranties().remove(warranty);
				this.adminService.save(a);
			}
		this.warrantyRepository.delete(warranty);
	}

	//FINDONE
	public Warranty findOne(int id) {
		Warranty res;
		res = this.warrantyRepository.findOne(id);
		//Assert.notNull(res);
		return res;
	}

	//FINDALL FINAL
	public Collection<Warranty> findAllFinal() {
		Collection<Warranty> res;
		res = this.warrantyRepository.findAllFinal();
		Assert.notNull(res);
		return res;
	}

	//FINDALL DRAFT
	public Collection<Warranty> findAllDraft() {
		Collection<Warranty> res;
		res = this.warrantyRepository.findAllDraft();
		Assert.notNull(res);
		return res;
	}

	public Collection<Warranty> findAll() {
		Collection<Warranty> res;
		res = this.warrantyRepository.findAll();
		Assert.notNull(res);
		return res;
	}

}
