package com.sravan.userservice.exceptionHandlers;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sravan.userservice.controller.UserController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	private static Logger log = Logger.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = SQLException.class)
	public String handleSqlException(SQLException e) {
		return "Database exception";
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public String handleException(Exception e) {
		log.error(e.getMessage());
		return e.getMessage();
	}

}