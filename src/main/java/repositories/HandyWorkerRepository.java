
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.HandyWorker;

@Repository
public interface HandyWorkerRepository extends JpaRepository<HandyWorker, Integer> {

	// The listing of handy workers who have got accepted at least 10% more applications than the average, ordered by number of applications.

	// The top-three handy workers in terms of complaints.  
	@Query("select h.name from HandyWorker h order by h.complaints.size desc")
	Collection<Customer> customersOrderByComplaints();

	@Query("select h from HandyWorker h where h.userAccount.id=?1")
	HandyWorker findByPrincipal(int id);
}
