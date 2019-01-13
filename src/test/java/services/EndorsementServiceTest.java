
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Endorsement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class EndorsementServiceTest extends AbstractTest {

	//Service under test
	@Autowired
	private EndorsementService	endorsementService;


	//test

	@Test
	public void testCreateEndorsement() {
		Endorsement res = new Endorsement();
		Assert.notNull(res);
		super.authenticate(null);
	}

	@Test
	public void testFindAllEndorsements() {
		Collection<Endorsement> endorsements = this.endorsementService.findAll();

		for (Endorsement e : endorsements)
			System.out.println(e.getId() + " " + e.getDate());
	}

	@Test
	public void testSaveEndorsement() {
		Endorsement e, saved;
		Collection<Endorsement> endorsements;

		e = this.endorsementService.findOne(970);
		e.setDate(LocalDate.now().toDate());
		Collection<String> comm = new ArrayList<>();
		comm.add("X");
		e.setComments(comm);

		saved = this.endorsementService.save(e);
		endorsements = this.endorsementService.findAll();

		Assert.isTrue(endorsements.contains(saved));
	}

	@Test
	public void testDeleteEndorsement() {
		Endorsement d;
		Collection<Endorsement> endorsements;

		d = this.endorsementService.findOne(970);
		this.endorsementService.delete(d);
		endorsements = this.endorsementService.findAll();

		Assert.isTrue(!endorsements.contains(d));
	}

}
