package com.sravan.userservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sravan.userservice.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
