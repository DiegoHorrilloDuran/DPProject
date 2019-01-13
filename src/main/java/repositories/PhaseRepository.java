
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.HandyWorker;
import domain.Phase;
import domain.Workplan;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Integer> {

	@Query("select h from HandyWorker h join h.workplans w join w.phases p where p.id=?1 group by h")
	HandyWorker getHandyWorkerByPhase(int phaseId);

	@Query("select w from Workplan w join w.phases p where p.id=?1")
	Workplan getWorkplanByPhase(int phaseId);

}
