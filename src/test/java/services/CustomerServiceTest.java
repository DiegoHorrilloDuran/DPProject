
package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CustomerServiceTest extends AbstractTest {

	//Service under test
	@Autowired
	private CustomerService	customerService;


	//test

	@Test
	public void testCreateCustomer() {
		Customer res = new Customer();
		Assert.notNull(res);
		super.authenticate(null);
	}

	@Test
	public void testFindAllCustomers() {
		Collection<Customer> customers = this.customerService.findAll();

		for (Customer c : customers)
			System.out.println(c.getId() + " " + c.getName());
	}

	@Test
	public void testSaveCustomer() {
		Customer c, saved;
		Collection<Customer> customers;

		c = this.customerService.findOne(975);
		c.setName("X");
		c.setMiddleName("X");
		c.setSurname("X");
		c.setEmail("x@x.com");
		c.setPhoneNumber("666666666");
		c.setAddress("X");
		c.setCreditCard(null);
		saved = this.customerService.save(c);
		customers = this.customerService.findAll();

		Assert.isTrue(customers.contains(saved));
	}
	@Test
	public void testDeleteCustomer() {
		Customer d;
		d = this.customerService.findOne(975);
		this.customerService.delete(d);
		Assert.isTrue(!this.customerService.findAll().contains(d));
	}

}
