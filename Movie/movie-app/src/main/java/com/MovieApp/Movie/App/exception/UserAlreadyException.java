package com.MovieApp.Movie.App.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "User already exist in the database")
public class UserAlreadyException extends Exception{
}
