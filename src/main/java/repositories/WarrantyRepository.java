
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Warranty;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Integer> {

	@Query("select w from Warranty w where w.finalMode=true")
	Collection<Warranty> findAllFinal();
	@Query("select w from Warranty w where w.finalMode=false")
	Collection<Warranty> findAllDraft();

}
