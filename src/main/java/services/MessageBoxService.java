
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageBoxRepository;
import security.LoginService;
import security.UserAccount;
import domain.Message;
import domain.MessageBox;

@Service
@Transactional
public class MessageBoxService {

	//Managed Repository-------------------------------------------------------

	@Autowired
	private MessageBoxRepository	messageBoxRepository;

	// Supporting services ----------------------------------------------------	

	@Autowired
	private MessageService			messageService;
	private ActorService			actorService;


	public MessageBoxService() {
		super();
	}

	//Simple CRUD methods-----------------------------------------------------

	public MessageBox create() {
		UserAccount u = LoginService.getPrincipal();
		Assert.isTrue(u.getId() != 0);
		MessageBox res = new MessageBox();
		return res;
	}

	public Collection<MessageBox> findAll() {
		Collection<MessageBox> res;
		res = this.messageBoxRepository.findAll();
		Assert.notNull(res);
		return res;

	}

	public MessageBox findOne(int id) {
		Assert.isTrue(id != 0);
		MessageBox res;
		res = this.messageBoxRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public MessageBox save(MessageBox m) {
		Assert.notNull(m);
		return this.messageBoxRepository.save(m);
	}

	public void delete(MessageBox m) {
		Assert.notNull(m);
		Collection<Message> mes = m.getMessages();
		String[] nombre = m.getBoxName().split(" ");
		if (m.getBoxName().contains("trashBox"))
			for (Message me : mes)
				this.messageService.delete(me);
		else {
			MessageBox dest = this.findMessageBoxByName(nombre[0], nombre[1]);
			for (Message me : mes)
				this.moveMessageToAnotherBox(me, dest);
		}
		this.messageBoxRepository.delete(m);
	}
	public MessageBox moveMessageToAnotherBox(Message m, MessageBox dest) {
		Collection<Message> mensajes;
		mensajes = dest.getMessages();
		mensajes.add(m);
		dest.setMessages(mensajes);
		return this.save(dest);
	}

	public MessageBox findMessageBoxByName(String tipo, String numero) {
		MessageBox res = null;
		Collection<MessageBox> mes = this.findAll();
		for (MessageBox m : mes)
			if (m.getBoxName().contains(tipo) && m.getBoxName().contains(numero))
				res = m;
		return res;
	}
}
