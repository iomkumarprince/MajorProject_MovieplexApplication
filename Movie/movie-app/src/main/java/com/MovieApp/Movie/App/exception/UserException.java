package com.MovieApp.Movie.App.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "User is not present")
public class UserException extends Exception{
    public UserException() {

    }
}
