
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SectionRepository;
import domain.Section;
import domain.Tutorial;

@Service
@Transactional
public class SectionService {

	@Autowired
	private SectionRepository	sectionRepository;
	@Autowired
	private TutorialService		tutorialService;


	public SectionService() {
		super();
	}

	public Section create() {
		Collection<String> pictures = new ArrayList<String>();
		Section res = new Section();
		res.setPictures(pictures);
		return res;
	}

	public Section findOne(int id) {
		Section res;
		res = this.sectionRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Section save(Section section) {
		Assert.notNull(section);
		return this.sectionRepository.save(section);
	}

	public void delete(Section section) {
		Assert.notNull(section);
		Collection<Tutorial> tur = this.tutorialService.findAll();
		for (Tutorial t : tur)
			if (t.getSections().contains(section)) {
				t.getSections().remove(section);
				this.tutorialService.save(t);
			}
		this.sectionRepository.delete(section);
	}

	public Collection<Section> findAll() {
		Collection<Section> res;
		res = this.sectionRepository.findAll();
		Assert.notNull(res);
		return res;
	}

}
