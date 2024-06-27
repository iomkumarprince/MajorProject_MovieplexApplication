package com.MovieApp.Movie.App.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "User is not present")
public class UserNotFoundException extends Exception {
}
