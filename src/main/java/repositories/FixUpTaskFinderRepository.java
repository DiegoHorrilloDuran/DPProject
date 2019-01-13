
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.FixUpTaskFinder;

@Repository
public interface FixUpTaskFinderRepository extends JpaRepository<FixUpTaskFinder, Integer> {

}
