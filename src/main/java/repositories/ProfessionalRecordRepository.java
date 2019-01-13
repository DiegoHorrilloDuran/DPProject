
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.HandyWorker;
import domain.ProfessionalRecord;

@Repository
public interface ProfessionalRecordRepository extends JpaRepository<ProfessionalRecord, Integer> {

	@Query("select h from HandyWorker h join h.curriculum c join c.professionalRecords pr where pr.id=? group by h")
	HandyWorker getHandyWorkerByProfessionalRecord(int phaseId);

}
