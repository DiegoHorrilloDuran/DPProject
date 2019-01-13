
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// The listing of customers who have published at least 10% more fix-up tasks than the average, ordered by number of applications. 
	@Query("select c from Customer c join c.fixUpTasks f join f.applications a group by c having count(f)>= (1.1*(select avg(cust.fixUpTasks.size)from Customer cust)) order by count(a) desc")
	Collection<Customer> customersOrderByApplications();

	// The top-three customers in terms of complaints. 
	@Query("select c.name from Customer c order by c.complaints.size desc")
	Collection<Customer> customersOrderByComplaints();

	@Query("select c from Customer c where c.userAccount.id=?1")
	Customer findByPrincipal(int id);

	@Query("select c from Customer c where c.creditCard.id=?1")
	Customer findByCreditCard(int id);
}
