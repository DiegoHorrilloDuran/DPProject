
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
import domain.CreditCard;
import domain.CreditCardBrand;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CreditCardServiceTest extends AbstractTest {

	// Service under test

	@Autowired
	private CreditCardService	creditcardService;


	// test

	@Test
	public void testCreateCreditCard() {
		CreditCard res = new CreditCard();
		Assert.notNull(res);
		super.authenticate(null);
	}

	@Test
	public void testSaveCreditCard() {
		CreditCard creditCard, saved;
		Collection<CreditCard> creditCards;

		creditCard = this.creditcardService.create();
		creditCard.setBrand(CreditCardBrand.VISA);
		creditCard.setHolderName("X");
		creditCard.setNumber(1111222233334444L);
		creditCard.setExpirationMonth(6);
		creditCard.setExpirationYear(20);
		creditCard.setCw(888);
		saved = this.creditcardService.save(creditCard);

		creditCards = this.creditcardService.findAll();
		Assert.isTrue(creditCards.contains(saved));
	}
	@Test
	public void testDeleteCreditCard() {
		CreditCard d;
		Collection<CreditCard> cards;

		d = this.creditcardService.findOne(964);
		this.creditcardService.delete(d);
		cards = this.creditcardService.findAll();

		Assert.isTrue(!cards.contains(d));

	}

	@Test
	public void testFindAllCreditCards() {
		Collection<CreditCard> CreditCards = this.creditcardService.findAll();

		for (CreditCard c : CreditCards)
			System.out.println(c.getId() + " " + c.getNumber());
	}

}
