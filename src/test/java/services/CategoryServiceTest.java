
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Category;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CategoryServiceTest extends AbstractTest {

	@Autowired
	private CategoryService	categoryService;


	@Test
	public void testCreate() {
		super.authenticate("adminUserName1");

		Category c = this.categoryService.create();
		Assert.notNull(c);
		super.authenticate(null);

	}

	@Test
	public void testDelete() {

		super.authenticate("adminUserName1");
		String name = "Name 2";
		Category res = null;
		for (Category c : this.categoryService.findAll())
			if (c.getName().equals(name))
				res = c;
		this.categoryService.delete(res);
		Assert.isTrue(!this.categoryService.findAll().contains(res));
		super.authenticate(null);

	}

	@Test
	public void testSave() {
		super.authenticate("adminUserName1");
		String name = "Name 1";
		Category res = null;
		for (Category c : this.categoryService.findAll())
			if (c.getName().equals(name))
				res = c;
		res.setName("Name 2");
		Category cat = this.categoryService.save(res);
		String name2 = "Name 2";
		Assert.isTrue(cat.getName().equals(name2));
		super.authenticate(null);
	}
}
