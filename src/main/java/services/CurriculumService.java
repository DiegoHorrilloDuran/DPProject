
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Curriculum;

@Service
@Transactional
public class CurriculumService {

	// Managed repository -------------------------------------
	@Autowired
	private CurriculumRepository	curriculumRepository;


	// Supporting services ------------------------------------

	public CurriculumService() {
		super();
	}

	// CRUD methods -------------------------------------------
	public Curriculum create() {
		Curriculum res = new Curriculum();
		return res;
	}

	public Collection<Curriculum> findAll() {
		Collection<Curriculum> res;
		res = this.curriculumRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Curriculum findOne(int id) {
		Assert.isTrue(id != 0);
		Curriculum res;
		res = this.curriculumRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Curriculum save(Curriculum c) {
		Assert.notNull(c);
		return this.curriculumRepository.save(c);
	}

	public void delete(Curriculum c) {
		Assert.notNull(c);
		this.curriculumRepository.delete(c);
	}

}
