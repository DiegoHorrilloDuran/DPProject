
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Complaint;
import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

	// The minimum, the maximum, the average, and the standard deviation of the
	// number of notes per referee report
	@Query("select avg(1.0*(select count(n.report) from Note n where n.report.id=rp.id)), min(1*(select count(n.report)" + " from Note n where n.report.id=rp.id)), max(1*(select count(n.report) from Note n where n.report.id=rp.id)),"
		+ "stddev(1.0*(select count(n.report) from Note n where n.report.id=rp.id)) from Report rp")
	Collection<Double> notesPerReport();

	@Query("select h from HandyWorker h join h.complaints c join c.reports r where h.id=?1 and r.finalMode=true")
	Collection<Report> getAllReportsByHandyWorker(int idHandyWorker);

	@Query("select r from Report r where r.complaint=?1")
	Collection<Report> findAllReportsByComplaint(Complaint complaint);

}
