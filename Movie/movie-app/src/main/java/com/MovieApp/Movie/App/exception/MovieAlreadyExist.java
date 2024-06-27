package com.MovieApp.Movie.App.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Movie is already present in the FavouriteList")
public class MovieAlreadyExist extends Exception {
}
