package com.MovieApp.User.Authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "User already exist in the database")
public class UserAlreadyException extends Exception{
}
