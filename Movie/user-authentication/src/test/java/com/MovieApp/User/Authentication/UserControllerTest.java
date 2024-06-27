package com.MovieApp.User.Authentication;

import com.MovieApp.User.Authentication.Controller.UserController;
import com.MovieApp.User.Authentication.domain.User;
import com.MovieApp.User.Authentication.domain.UserDto;
import com.MovieApp.User.Authentication.exception.UserAlreadyException;
import com.MovieApp.User.Authentication.exception.UserException;
import com.MovieApp.User.Authentication.services.TokenGeneratorImpl;
import com.MovieApp.User.Authentication.services.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserServiceImpl userService;
    @Mock
    private TokenGeneratorImpl tokenGenerator;
    @InjectMocks
    private UserController userController;
    @Autowired
    private MockMvc mockMvc;
    private User user;
    private UserDto userDto;
    @BeforeEach
    public void setUp(){
        user = new User("Kumar Prince", "kumarprincelkh@gmail.com", "secret456", "9326688553", "/path/to/profile.jpg");
        userDto = new UserDto(user.getName(),user.getEmail(),user.getPassword(),user.getPhoneNo(),user.getImageName());
        mockMvc= MockMvcBuilders.standaloneSetup(userController).build();

    }
    @AfterEach
    public void tearDown(){
        user=null;
        userDto= null;
    }
    @Test
    public void addUserSuccess() throws Exception {
        when(userService.registerUser(user)).thenReturn(user);
        mockMvc.perform(
                post("/api/v1/RegisterUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertToJson(userDto)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(userService,times(1)).registerUser(user);
    }
    @Test
    public  void addUserFailure() throws Exception {
        when(userService.registerUser(user)).thenThrow(UserAlreadyException.class);
        mockMvc.perform(
                        post("/api/v1/RegisterUser")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(userDto)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(userService,times(1)).registerUser(user);
    }
    @Test
    public void deleteUserSuccess() throws Exception {
        when(userService.deleteUser(user.getEmail())).thenReturn(true);
        mockMvc.perform(
                        delete("/api/v1/deleteUser/kumarprincelkh@gmail.com"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(userService,times(1)).deleteUser(user.getEmail());
    }
    @Test
    public void deleteUserFailure() throws Exception {
        when(userService.deleteUser("kumarprincelkh@gmail.com")).thenThrow(UserException.class);
        mockMvc.perform(
                        delete("/api/v1/deleteUser/kumarprincelkh@gmail.com"))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
        verify(userService,times(1)).deleteUser("kumarprincelkh@gmail.com");
    }
    @Test
    public void logInSuccess() throws Exception {
        when(userService.loginUser(user)).thenReturn(user);
        mockMvc.perform(
                post("/api/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertToJson(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(userService,times(1)).loginUser(user);
    }
    @Test
    public void logInFailure() throws Exception {
        when(userService.loginUser(user)).thenThrow(UserException.class);
        mockMvc.perform(
                        post("/api/v1/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(user)))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
        verify(userService,times(1)).loginUser(user);
    }



    public  static  String convertToJson(final Object object){
        String result="";
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
