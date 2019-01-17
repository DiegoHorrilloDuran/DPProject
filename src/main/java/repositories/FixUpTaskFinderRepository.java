
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FixUpTask;
import domain.FixUpTaskFinder;

@Repository
public interface FixUpTaskFinderRepository extends JpaRepository<FixUpTaskFinder, Integer>{
	
	@Query("select w.finder from Worker w where w.id=?1")
    public FixUpTaskFinder getFinderByLoggedWorker(int workerId);

    @Query("select t from Task t where t.category.name=?1")
    public Collection<FixUpTask> filterTasksByCategory(String category);

    @Query("select t from Task t where t.warranty.title=?1")
    public Collection<FixUpTask> filterTasksByWarranty(String warranty);

    @Query("select t from Task t where t.maxPrice<=?1")
    public Collection<FixUpTask> filterTasksByMaxPrice(Double maxPrice);

    @Query("select t from Task t where t.maxPrice>=?1")
    public Collection<FixUpTask> filterTasksByMinPrice(Double minPrice);

    @Query("select t from Task t")
    public Collection<FixUpTask> getAllTasks();

    @Query("select t from Task t where t.description like %?1% ")
    public Collection<FixUpTask> filterTasksByKeyWordInDescription(String keyWord);

    @Query("select t from Task t where t.ticker like %?1% ")
    public Collection<FixUpTask> filterTasksByKeyWordInTicker(String keyWord);

    @Query("select t from Task t where t.address like %?1% ")
    public Collection<FixUpTask> filterTasksByKeyWordInAddress(String keyWord);

    @Query("select t from Task t where t.startDate>=?1 ")
    public Collection<FixUpTask> filterTasksByStartDate(Date startDate);

    @Query("select t from Task t where t.endDate<=?1 ")
    public Collection<FixUpTask> filterTasksByEndDate(Date endDate);


}
