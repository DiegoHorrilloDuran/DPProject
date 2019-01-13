
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FixUpTask;

@Repository
public interface FixUpTaskRepository extends JpaRepository<FixUpTask, Integer> {

	// The average, the minimum, the maximum, and the standard deviation of the number of applications per fix-up task
	@Query("select avg(1.0*(select count(ap.fixUpTask) from Application ap where ap.fixUpTask=fx.id))," + "min(1*(select count(ap.fixUpTask) from Application ap where ap.fixUpTask=fx.id)), "
		+ "max(1*(select count(ap.fixUpTask) from Application ap where ap.fixUpTask=fx.id))," + "stddev(1.0*(select count(ap.fixUpTask) " + "from Application ap where ap.fixUpTask=fx.id)) from FixUpTask fx")
	Collection<Double> applicationsPerFixUpTasks();

	// The average, the minimum, the maximum, and the standard deviation of the maximum price of the fix-up tasks
	@Query("select avg(fx.maxPrice.amount), min(fx.maxPrice.amount), max(fx.maxPrice.amount), stddev(fx.maxPrice.amount) from FixUpTask fx")
	Collection<Double> pricePerFixUpTasks();

	// The minimum, the maximum, the average, and the standard deviation of the number of complaints per fix-up task.
	@Query("select avg(1.0*(select count(com.fixUpTask) from Complaint com where com.fixUpTask=fx.id))," + "min(1*(select count(com.fixUpTask) from Complaint com where com.fixUpTask=fx.id)), "
		+ "max(1*(select count(com.fixUpTask) from Complaint com where com.fixUpTask=fx.id))," + "stddev(1.0*(select count(com.fixUpTask) from Complaint com where com.fixUpTask=fx.id)) from FixUpTask fx")
	Collection<Double> complaintsPerFixUpTasks();

	//The ratio of fix-up tasks with a complaint
	@Query("select 1*(select count(f) from FixUpTask f where f.complaints.size > 0),count(f) from FixUpTask f")
	Collection<Double> ratioFixUpTaskWithComplaint();

}
