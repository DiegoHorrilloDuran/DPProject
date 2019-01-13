
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import domain.CreditCard;
import domain.Customer;

@Service
@Transactional
public class CreditCardService {

	public CreditCardService() {
		super();
	}


	// Managed repository -------------------------------------
	@Autowired
	private CreditCardRepository	creditCardRepository;

	// Supporting services ------------------------------------
	@Autowired
	private CustomerService			customerService;


	// CRUD methods -------------------------------------------

	public CreditCard create() {
		CreditCard cc = new CreditCard();
		return cc;
	}

	public Collection<CreditCard> findAll() {
		Collection<CreditCard> res;
		res = this.creditCardRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public CreditCard findOne(int id) {
		Assert.isTrue(id != 0);
		CreditCard res;
		res = this.creditCardRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public CreditCard save(CreditCard c) {
		Assert.notNull(c);
		return this.creditCardRepository.save(c);
	}

	public void delete(CreditCard c) {
		Assert.notNull(c);
		Integer i = c.getId();
		Assert.notNull(i);
		Customer cu = this.customerService.findByCreditCard(i);
		Assert.notNull(cu);
		cu.setCreditCard(null);
		this.customerService.save(cu);
		this.creditCardRepository.delete(c);
	}

}
