
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
import domain.Curriculum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CurriculumServiceTest extends AbstractTest {

	//Service under test
	@Autowired
	private CurriculumService	curriculumService;


	//test

	@Test
	public void testCreateCurriculum() {
		Curriculum res = new Curriculum();
		Assert.notNull(res);
		super.authenticate(null);
	}

	@Test
	public void testFindAllCurriculums() {
		Collection<Curriculum> curriculums = this.curriculumService.findAll();

		for (Curriculum c : curriculums)
			System.out.println(c.getId() + " " + c.getHandyWorker());
	}

	@Test
	public void testSaveCurriculum() {
		Curriculum c, saved;
		Collection<Curriculum> curriculums;

		c = this.curriculumService.findOne(981);
		c.setTicker("X");

		saved = this.curriculumService.save(c);
		curriculums = this.curriculumService.findAll();

		Assert.isTrue(curriculums.contains(saved));
	}

	@Test
	public void testDeleteCurriculum() {
		Curriculum d;
		d = this.curriculumService.findOne(981);
		this.curriculumService.delete(d);
		Collection<Curriculum> curriculums = this.curriculumService.findAll();
		Assert.isTrue(!curriculums.contains(d));
	}
}
