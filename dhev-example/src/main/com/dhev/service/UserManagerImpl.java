package com.dhev.service;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.dhev.model.User;

@Name("userManager")
public class UserManagerImpl implements UserManager {

	@In(create = true)
	User user;

	@In
	protected EntityManager entityManager;

	public String registerUser() throws Exception {
		System.out.println("Registering user " + user.getUserName());

		entityManager.persist(user);

		return "success";
	}

}
