
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.WordsList;

@Repository
public interface WordsListRepository extends JpaRepository<WordsList, Integer> {

}
