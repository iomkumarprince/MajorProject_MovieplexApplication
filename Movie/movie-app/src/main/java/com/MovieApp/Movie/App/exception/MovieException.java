package com.MovieApp.Movie.App.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Movie is not present in the FavouriteList")
public class MovieException extends Exception{
}
