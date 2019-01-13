
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SocialProfileRepository;
import domain.Actor;
import domain.SocialProfile;

@Service
@Transactional
public class SocialProfileService {

	@Autowired
	private SocialProfileRepository	socialProfileRepository;
	@Autowired
	private ActorService			actorService;


	public SocialProfileService() {
		super();
	}

	public SocialProfile create() {
		SocialProfile res = new SocialProfile();
		return res;
	}

	public SocialProfile save(SocialProfile socialProfile) {
		Assert.notNull(socialProfile);
		return this.socialProfileRepository.save(socialProfile);
	}

	public SocialProfile findOne(int id) {
		SocialProfile res;
		res = this.socialProfileRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public void delete(SocialProfile socialProfile) {
		Assert.notNull(socialProfile);
		Collection<Actor> act = this.actorService.findAll();
		for (Actor a : act)
			if (a.getSocialProfiles().contains(socialProfile)) {
				a.getSocialProfiles().remove(socialProfile);
				this.actorService.save(a);
			}
		this.socialProfileRepository.delete(socialProfile);
	}

	public Collection<SocialProfile> findAll() {
		Collection<SocialProfile> res;
		res = this.socialProfileRepository.findAll();
		Assert.notNull(res);
		return res;
	}
}
