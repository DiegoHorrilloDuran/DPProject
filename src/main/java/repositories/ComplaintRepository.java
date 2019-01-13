
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

	@Query("select c from Complaint c where c.referee = null")
	Collection<Complaint> findAllUnasigned();

	@Query("select c from Complaint c where c.ticker = ?1")
	Complaint findByTicker(String ticker);
}
