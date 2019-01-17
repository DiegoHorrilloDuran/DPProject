/*
 * LoginService.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import security.UserAccount;
import security.UserAccountRepository;

@Service
@Transactional
public class UserAccountService {

	// Managed repository -----------------------------------------------------

	@Autowired
	UserAccountRepository	userRepository;


	// Business methods -------------------------------------------------------

	public UserAccountService() {
		super();
	}

	public UserAccount create() {

		final UserAccount userAccount = new UserAccount();

		return userAccount;

	}
	public UserAccount save(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		return this.userRepository.save(userAccount);

	}

	public UserAccount findOne(final int id) {
		Assert.isTrue(id != 0);
		UserAccount res;
		res = this.userRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

}
