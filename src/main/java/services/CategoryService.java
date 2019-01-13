
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Admin;
import domain.Category;
import domain.FixUpTask;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository	categoryRepository;

	@Autowired
	private AdminService		adminService;

	@Autowired
	private FixUpTaskService	fisxUpTaskService;


	public CategoryService() {
		super();
	}

	public Category create() {

		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		//An admin call the method
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Category c = new Category();
		return c;

	}

	public Collection<Category> findAll() {
		Collection<Category> res;
		res = this.categoryRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Category findOne(int id) {
		Assert.isTrue(id != 0);
		Category res;
		res = this.categoryRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Category save(final Category category) {
		Assert.notNull(category);
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		//An admin call the method
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));

		return this.categoryRepository.save(category);
	}

	public void delete(Category category) {
		Assert.notNull(category);
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		//An admin call the method
		Authority au = new Authority();
		au.setAuthority("ADMIN");
		Assert.isTrue(userAccount.getAuthorities().contains(au));
		Collection<FixUpTask> fix = this.fisxUpTaskService.findAll();
		for (FixUpTask f : fix)
			if (f.getCategory().equals(category))
				this.fisxUpTaskService.delete(f);
		Collection<Admin> adm = this.adminService.findAll();
		for (Admin a : adm)
			if (a.getCataloguesOfCategories().contains(category)) {
				a.getCataloguesOfCategories().remove(category);
				this.adminService.save(a);
			}
		Collection<Category> cat = this.findAll();
		for (Category c : cat)
			if (c.getChildren().contains(category)) {
				c.getChildren().remove(category);
				this.save(c);
			}

		this.categoryRepository.delete(category);
	}

}
