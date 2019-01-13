
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Customer;
import domain.Endorsement;

@Service
@Transactional
public class CustomerService {

	//Managed repository -------------------------------------
	@Autowired
	private CustomerRepository	customerRepository;

	@Autowired
	private EndorsementService	endorsementService;


	//Supporting services ------------------------------------

	public CustomerService() {
		super();
	}

	//CRUD methods -------------------------------------------

	public Customer create() {
		Customer res = new Customer();
		return res;
	}

	public Collection<Customer> findAll() {
		Collection<Customer> res;
		res = this.customerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Customer findOne(int id) {
		Assert.isTrue(id != 0);
		Customer res;
		res = this.customerRepository.findOne(id);
		//		Assert.notNull(res);
		return res;
	}

	public Customer save(Customer c) {
		Assert.notNull(c);
		return this.customerRepository.save(c);
	}

	public void delete(Customer c) {
		Assert.notNull(c);
		Collection<Endorsement> end = this.endorsementService.findAll();
		for (Endorsement e : end) {
			if (e.getCustomerRecipient().equals(c)) {
				e.setCustomerRecipient(null);
				this.endorsementService.save(e);
			}
			if (e.getCustomerSender().equals(c)) {
				e.setCustomerSender(null);
				this.endorsementService.save(e);
			}
		}
		this.customerRepository.delete(c);
	}

	public Customer findByPrincipal() {
		UserAccount userAccount;
		Customer c;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount, "User Account is null.");
		c = this.customerRepository.findByPrincipal(userAccount.getId());
		return c;
	}

	public Customer findByCreditCard(int id) {
		Assert.isTrue(id != 0);
		Customer c;
		c = this.customerRepository.findByCreditCard(id);
		Assert.notNull(c);
		return c;
	}

}
