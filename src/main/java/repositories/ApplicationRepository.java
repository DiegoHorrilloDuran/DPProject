
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	// The average, the minimum, the maximum, and the standard deviation of the price offered in the applications.
	@Query("select avg(ap.offeredPrice.amount),min(ap.offeredPrice.amount),max(ap.offeredPrice.amount),stddev(ap.offeredPrice.amount) from Application ap")
	Collection<Double> pricePerApplications();

	// The ratio of pending applications
	@Query("select 1*(select count(ap) from Application ap where ap.status = domain.Status.PENDING),count(a) from Application a")
	Collection<Double> ratioOfPendingApplications();

	// The ratio of accepted application
	@Query("select 1*(select count(ap) from Application ap where ap.status = domain.Status.ACCEPTED),count(a) from Application a")
	Collection<Double> ratioOfAcceptedApplications();

	// The ratio of rejected applications.
	@Query("select 1*(select count(ap) from Application ap where ap.status = domain.Status.REJECTED),count(a) from Application a")
	Collection<Double> ratioOfRejectedApplications();

	// The ratio of pending applications that cannot change its status because their time period's elapsed
	@Query("select 1*(select count(ap) from Application ap where ap.status = domain.Status.PENDING and ap.fixUpTask.carryOutDate < CURRENT_DATE),count(a) from Application a")
	Collection<Double> ratioOfPendingApplicationsOutOfDate();

}
