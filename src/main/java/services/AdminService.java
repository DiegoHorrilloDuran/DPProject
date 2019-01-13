
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import security.LoginService;
import security.UserAccount;
import domain.Admin;

@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminRepository	adminRepository;

	
	public Admin create(){
		return new Admin();
	}

	public AdminService() {
		super();
	}

	public void delete(Admin admin) {
		Assert.notNull(admin);
		Assert.isTrue(admin.getId() != 0);
		this.adminRepository.delete(admin);

	}

	public Admin save(Admin admin) {
		Assert.notNull(admin);
		return this.adminRepository.save(admin);

	}

	public Admin findOne(int id) {
		Assert.isTrue(id != 0);
		Admin res;
		res = this.adminRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Collection<Admin> findAll() {
		Collection<Admin> res;
		res = this.adminRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Admin findByPrincipal() {
		UserAccount userAccount;
		Admin admin;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount, "User Account is null.");
		admin = this.adminRepository.findByPrincipal(userAccount.getId());
		return admin;
	}

}
