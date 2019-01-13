
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Section;
import domain.Tutorial;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {

	@Query("select t from Tutorial t join t.sections s where s.id=?1")
	Tutorial getTutorials(int id);

}
