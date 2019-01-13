
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TutorialRepository;
import domain.Actor;
import domain.Section;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	@Autowired
	private TutorialRepository	tutorialRepository;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private SponsorshipsService	sponsorshipsService;


	public TutorialService() {
		super();
	}

	//CREATE
	public Tutorial create() {
		Collection<Section> sections = new ArrayList<Section>();
		Collection<String> pictures = new ArrayList<String>();
		Tutorial res = new Tutorial();
		res.setSections(sections);
		res.setPictures(pictures);
		return res;
	}

	//SAVE
	public Tutorial save(Tutorial tutorial) {
		Assert.notNull(tutorial);
		return this.tutorialRepository.save(tutorial);
	}

	//DELETE
	public void delete(Tutorial tutorial) {
		Assert.notNull(tutorial);
		Collection<Actor> act = this.actorService.findAll();
		for (Actor a : act)
			if (a.getTutorials().contains(tutorial)) {
				a.getTutorials().remove(tutorial);
				this.actorService.save(a);
			}
		this.tutorialRepository.delete(tutorial);
	}

	//FINDONE
	public Tutorial findOne(int id) {
		Tutorial res;
		res = this.tutorialRepository.findOne(id);
		return res;
	}

	//FINDALL
	public Collection<Tutorial> findAll() {
		Collection<Tutorial> res;
		res = this.tutorialRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	//FINDTUTORIALS BY HANDYWORKER
	public Collection<Tutorial> findTutorialsByHandyWorker(int id) {
		Collection<Tutorial> res;
		res = this.tutorialRepository.findTutorialsByHandyWorker(id);
		Assert.notNull(res);
		return res;
	}

}
