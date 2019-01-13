
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Message;

@Service
@Transactional
public class MessageService {

	//Managed Repository-------------------------------------------------------

	@Autowired
	private MessageRepository	messageRepository;

	// Supporting services ----------------------------------------------------	

	@Autowired
	private ActorService		actorService;


	public MessageService() {
		super();
	}

	//Simple CRUD methods-----------------------------------------------------

	public Message create() {
		Actor a = this.actorService.findByPrincipal();
		Message res = new Message();
		res.setSender(a);
		return res;
	}

	public Collection<Message> findAll() {
		Collection<Message> res;
		res = this.messageRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Message findOne(int id) {
		Assert.isTrue(id != 0);
		Message res;
		res = this.messageRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Message save(Message m) {
		Assert.notNull(m);
		return this.messageRepository.save(m);
	}

	public void delete(Message m) {
		Assert.notNull(m);
		this.messageRepository.delete(m);
	}
	public Collection<Message> findAllByActorSender() {

		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		//A customer call the method

		Collection<Message> res = new ArrayList<Message>();

		for (Message m : this.messageRepository.findAll())
			if (m.getSender().getId() == userAccount.getId())
				res.add(m);
		return res;
	}
	public Collection<Message> findAllByActorRecipient() {

		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		//A customer call the method

		Collection<Message> res = new ArrayList<Message>();

		for (Message m : this.messageRepository.findAll())
			if (m.getRecipient().getId() == userAccount.getId())
				res.add(m);
		return res;
	}

}
