
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	// The average, the minimum, the maximum, and the standard deviation of the number of fix-up tasks per user.
	@Query("select avg(a.fixUpTasks.size), min(a.fixUpTasks.size),max(a.fixUpTasks.size),stddev(a.fixUpTasks.size) from Actor a")
	Collection<Double> fixUpTasksPerUser();

	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findByUserAccount(int id);

}
