package com.MovieApp.User.Authentication;

import com.MovieApp.User.Authentication.domain.User;
import com.MovieApp.User.Authentication.exception.UserAlreadyException;
import com.MovieApp.User.Authentication.exception.UserException;
import com.MovieApp.User.Authentication.repository.UserRepo;
import com.MovieApp.User.Authentication.services.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepo userRepo;
    @InjectMocks
    private UserServiceImpl userService;
    private User user;
    @BeforeEach
    public void setUp(){
        user = new User("Kumar Prince", "kumarprincelkh@gmail.com", "secret456", "9326688553", "/path/to/profile.jpg");
    }
    @AfterEach
    public void tearDown(){
        user=null;
        userRepo.deleteAll();

    }
    @Test
    public void registerUserSuccess() throws UserAlreadyException {
        when(userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(null);
        when(userRepo.save(user)).thenReturn(user);

        User insertedUser = userService.registerUser(user);

        assertEquals(insertedUser, user);
        verify(userRepo, times(1)).findByEmailAndPassword(user.getEmail(), user.getPassword());
        verify(userRepo, times(1)).save(user);
    }
    @Test
    public void registerUserFailure(){
        when(userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(user);
        assertThrows(UserAlreadyException.class,()->userService.registerUser(user));
        verify(userRepo,times(1)).findByEmailAndPassword(user.getEmail(),user.getPassword());
        verify(userRepo,times(0)).save(user);

    }
    @Test
    public void deleteUserSuccess() throws UserException {
        when(userRepo.findByEmail(user.getEmail())).thenReturn(user);
        boolean deletedUser = userService.deleteUser(user.getEmail());
        assertTrue(deletedUser);
        verify(userRepo,times(1)).findByEmail(user.getEmail());

    }
    @Test
    public void deleteUserFailure(){
        when(userRepo.findByEmail(user.getEmail())).thenReturn(null);
        assertThrows(UserException.class,()->userService.deleteUser(user.getEmail()));
        verify(userRepo,times(1)).findByEmail(user.getEmail());
    }
    @Test
    public void testLogInSuccess() throws UserException {
        when(userRepo.findByEmailAndPassword(user.getEmail(),user.getPassword())).thenReturn(user);
        User user1 = userService.loginUser(user);
        assertEquals(user1,user);
        verify(userRepo,times(1)).findByEmailAndPassword(user.getEmail(),user.getPassword());
    }
    @Test
    public void testLoginFailure(){
        when(userRepo.findByEmailAndPassword(user.getEmail(),user.getPassword())).thenReturn(null);
        assertThrows(UserException.class,()-> userService.loginUser(user));
        verify(userRepo,times(1)).findByEmailAndPassword(user.getEmail(),user.getPassword());
    }
}
