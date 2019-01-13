
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.MessageBox;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository		actorRepository;

	@Autowired
	private MessageBoxService	messageBoxService;


	public ActorService() {
		super();
	}

	public Actor findOne(int id) {
		Assert.isTrue(id != 0);

		Actor res;

		res = this.actorRepository.findOne(id);
		Assert.notNull(res);
		return res;

	}

	public Collection<Actor> findAll() {
		Collection<Actor> res;
		res = this.actorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Actor save(Actor actor) {
		Assert.notNull(actor);
		return this.actorRepository.save(actor);
	}

	public Actor findByUserAccount(UserAccount userAccount) {
		Actor result;

		result = this.actorRepository.findByUserAccount(userAccount.getId());
		return result;
	}

	public Actor findByPrincipal() {
		UserAccount userAccount;
		Actor res;
		userAccount = LoginService.getPrincipal();
		res = this.findByUserAccount(userAccount);
		return res;
	}

	// Obtener el cliente logueado
	public Actor getLoggedClient() {
		UserAccount a = LoginService.getPrincipal();
		String username = a.getUsername();
		Actor cAux = null;
		for (Actor c : this.findAll())
			if (c.getUserAccount().getUsername().equals(username))
				cAux = c;
		return cAux;
	}

	// Default MessageBox Create
	public void defaultMessageBoxes(Actor actor) {
		Collection<MessageBox> messageBoxes = new ArrayList<MessageBox>();

		MessageBox mb1 = this.messageBoxService.create();
		mb1.setBoxName("inbox");
		messageBoxes.add(mb1);
		MessageBox mb2 = this.messageBoxService.create();
		mb2.setBoxName("outbox");
		messageBoxes.add(mb2);
		MessageBox mb3 = this.messageBoxService.create();
		mb3.setBoxName("trashbox");
		messageBoxes.add(mb3);
		MessageBox mb4 = this.messageBoxService.create();
		mb4.setBoxName("spambox");
		messageBoxes.add(mb4);
		actor.setMessageBoxes(messageBoxes);
	}

}
