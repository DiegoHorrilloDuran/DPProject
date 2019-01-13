
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
import domain.Complaint;
import domain.Report;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ReportServiceTest extends AbstractTest {

	@Autowired
	private ReportService		reportService;

	@Autowired
	private HandyWorkerService	handyWorkerService;

	@Autowired
	private ComplaintService	complaintService;


	@Test
	public void testCreate() {
		String ticker = "181114-US2018";
		Complaint c = this.complaintService.findByTicker(ticker);
		Report r = this.reportService.create(c);
		Assert.notNull(r);
	}
	@Test
	public void testSave() {
		Report r, saved;
		Collection<Report> report;

		r = this.reportService.findOne(1050);
		r.setDescription("x");

		saved = this.reportService.save(r);
		report = this.reportService.findAll();

		Assert.isTrue(report.contains(saved));
	}

	@Test
	public void testDelete() {
		Report r = this.reportService.findOne(1050);
		this.reportService.delete(r);
		Collection<Report> res = this.reportService.findAll();
		Assert.isTrue(!(res.contains(r)));
	}

	@Test
	public void testFindAll() {
		Collection<Report> res = this.reportService.findAll();
		Assert.isTrue(!res.isEmpty());
	}

}
