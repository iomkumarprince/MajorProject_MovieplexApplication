package com.MovieApp.User.Authentication.services;


import com.MovieApp.User.Authentication.domain.User;
import com.MovieApp.User.Authentication.exception.UserAlreadyException;
import com.MovieApp.User.Authentication.exception.UserException;

public interface UserService {
    public User registerUser(User user) throws UserAlreadyException;
    public User loginUser(User user) throws UserException;
    public User updateUser(String email,User user);
    public boolean deleteUser(String email) throws UserException;
    public User updatePassword(String email,String password) throws UserException;

}
