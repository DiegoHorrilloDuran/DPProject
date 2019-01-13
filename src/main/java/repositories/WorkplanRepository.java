
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Workplan;

@Repository
public interface WorkplanRepository extends JpaRepository<Workplan, Integer> {

}
