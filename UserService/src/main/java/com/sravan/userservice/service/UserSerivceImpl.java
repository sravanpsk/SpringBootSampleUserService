package com.sravan.userservice.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sravan.userservice.controller.UserController;
import com.sravan.userservice.model.User;
import com.sravan.userservice.repository.UserRepository;

@Component
public class UserSerivceImpl implements UserService {

	/*
	 * This is service will take care of controller data and sent data to
	 * repository
	 */
	private static Logger log = Logger.getLogger(UserSerivceImpl.class);

	UserRepository userRepo;

	public UserRepository getUserRepo() {
		return userRepo;
	}

	@Autowired
	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public User saveUser(User user) {
		log.debug("before saving user into DB");
		User savedUser=null;
		try{
		savedUser=userRepo.save(user);
		}catch (Exception e) {
			log.error("Unable save user in DB");
			throw e;
		}
		log.debug("After Saving user into DB");
		return savedUser;
	}

	@Override
	public boolean deleteUser(long id) {
		boolean flag=false;
		log.debug("before deleting user from DB");
		try{
			userRepo.delete(id);
			flag=true;
		}catch (Exception e) {
			log.error("Unable delete user from DB");
			throw e;
		}
		log.debug("After deleting user from DB");
		return flag;
	}

	@Override
	public User getUserInfo(long id) {
		User user=null;
		log.debug("before getting user from DB");
		try{
			user = userRepo.findOne(id);
		}catch (Exception e) {
			log.error("Unable find the user from DB");
			throw e;
		}
		log.debug("After getting user from DB");
		return user;
	
		
	}

}
