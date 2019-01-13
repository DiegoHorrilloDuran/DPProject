
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FixUpTaskFinder;
import domain.SearchResult;

@Repository
public interface SearchResultRepository extends JpaRepository<SearchResult, Integer> {

	@Query("select f from FixUpTaskFinder f where f.searchResult.id=?1")
	FixUpTaskFinder getFixUpTaskFinder(int id);

}
