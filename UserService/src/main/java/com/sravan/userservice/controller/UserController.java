package com.sravan.userservice.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sravan.userservice.SpringBootWebServiceApplication;
import com.sravan.userservice.model.User;
import com.sravan.userservice.service.UserService;

@RestController
@RequestMapping("/userService/user")
public class UserController {
	private static Logger log = Logger.getLogger(UserController.class);
	UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> saveUser(@RequestBody User user) {

		if (null != user) {
			userService.saveUser(user);
			log.debug("Added:: " + user);
		}
		return new ResponseEntity(user, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUser(@PathVariable("id") long id) {
		User user = userService.getUserInfo(id);
		if (user == null) {
			log.debug("user  with id" + id + " does not exists");
			return new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND);
		}
		log.debug("Found User:: " + user);
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
		User user = userService.getUserInfo(id);
		if (null != user) {
			userService.deleteUser(id);
			log.debug("deleted User:: with id" + id);
			return new ResponseEntity<String>("User Deleted Successfully", HttpStatus.GONE);
		} else {
			log.debug("user  with id" + id + " does not exists");
			return new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Object> updateUserInfo(@RequestBody User user) {
		User existingUser = userService.getUserInfo(user.getId());
		if (existingUser == null) {
			log.debug("User with id " + user.getId() + " does not exists");
			return new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND);
		} else {
			User updatedUser = userService.saveUser(user);
			log.debug("User updated" + updatedUser);
			return new ResponseEntity(updatedUser, HttpStatus.OK);
		}
	}

}
