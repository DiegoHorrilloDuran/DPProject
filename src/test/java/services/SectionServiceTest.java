
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
import domain.Section;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SectionServiceTest extends AbstractTest {

	@Autowired
	private SectionService	sectionService;


	@Test
	public void testCreate() {
		Section res = this.sectionService.create();
		Assert.notNull(res);
	}

	@Test
	public void testSave() {
		Section s, saved;
		Collection<Section> section;
		s = this.sectionService.findOne(1054);
		s.setTitle("x");
		s.setText("x");

		saved = this.sectionService.save(s);
		section = this.sectionService.findAll();

		Assert.isTrue(section.contains(saved));
	}

	@Test
	public void testDelete() {
		Section s;
		Collection<Section> section;

		s = this.sectionService.findOne(1054);
		this.sectionService.delete(s);
		section = this.sectionService.findAll();

		Assert.isTrue(!section.contains(s));
	}

	@Test
	public void testFindAll() {
		Collection<Section> section = this.sectionService.findAll();

		for (Section s : section)
			System.out.println(s.getId() + " " + s.getTitle());
	}
}
