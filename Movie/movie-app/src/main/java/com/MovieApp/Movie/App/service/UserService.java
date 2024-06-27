package com.MovieApp.Movie.App.service;

import com.MovieApp.Movie.App.domain.User;
import com.MovieApp.Movie.App.domain.Movie;
import com.MovieApp.Movie.App.exception.MovieAlreadyExist;
import com.MovieApp.Movie.App.exception.MovieException;
import com.MovieApp.Movie.App.exception.UserAlreadyException;
import com.MovieApp.Movie.App.exception.UserException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    String uploadImage( MultipartFile file) throws IOException;
    public byte[] getUserProfileImg(String email);
    public User addUser(User user) throws UserAlreadyException;
    public  boolean deleteUser(String id) throws UserException;
    public List<Movie>  getAllFavouriteMovie(String id) throws UserException;
    public User addMovieToFavorite(String email, Movie movies) throws MovieAlreadyExist, UserException;
    public User getUser(String email) throws UserException;
    public String updatePassword(String email,String password) throws UserException;
    public boolean deleteFavoriteMovie(String email,Movie movie) throws MovieException,UserException;
    User updateFullUserDetails(User updatedUser);
    User updateUserWithEmail(MultipartFile file, String userEmail, String userJson) throws IOException;

}
