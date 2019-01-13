
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Admin;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdminServiceTest extends AbstractTest {

	//Service under test -------------------------

	@Autowired
	private AdminService	adminService;


	@Test
	public void testDeleteAdmin() {

		//Delete admin -> username = AdminUserName2
		super.authenticate("AdminUserName2");
		Admin a = this.adminService.findByPrincipal();
		this.adminService.delete(a);
		Collection<Admin> ad = this.adminService.findAll();
		Assert.isTrue(!ad.contains(a));
		super.authenticate(null);

	}

	@Test
	public void testSaveAdmin() {
		super.authenticate("AdminUserName2");
		Admin a = this.adminService.findByPrincipal();
		a.setAddress("Address2");
		Admin s = this.adminService.save(a);
		Collection<Admin> res = this.adminService.findAll();
		Assert.isTrue(res.contains(s));
		super.authenticate(null);

	}

}
