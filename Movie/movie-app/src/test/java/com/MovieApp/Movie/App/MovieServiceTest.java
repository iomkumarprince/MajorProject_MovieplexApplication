//package com.MovieApp.Movie.App;
//
//import com.MovieApp.Movie.App.domain.Movie;
//import com.MovieApp.Movie.App.domain.User;
//import com.MovieApp.Movie.App.domain.UserDto;
//import com.MovieApp.Movie.App.exception.MovieAlreadyExist;
//import com.MovieApp.Movie.App.exception.MovieException;
//import com.MovieApp.Movie.App.exception.UserAlreadyException;
//import com.MovieApp.Movie.App.exception.UserException;
//import com.MovieApp.Movie.App.proxy.MovieProxy;
//import com.MovieApp.Movie.App.repository.UserRepository;
//import com.MovieApp.Movie.App.service.UserServiceImpl;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class MovieServiceTest {
//    @Mock
//    private UserRepository userRepository;
//    @InjectMocks
//    private UserServiceImpl userService;
//    @Mock
//    private MovieProxy movieProxy;
//    private User user;
//    private Movie movie;
//
//    @BeforeEach
//    public void tearUp(){
//        int movieId = 1234;
//        String originalTitle = "Example Movie";
//        String posterPath = "/path/to/poster.jpg";
//        String overview = "This is an example movie.";
//        List<Movie> movieList = new ArrayList<>();
//
//        movie = new Movie(movieId, originalTitle, posterPath, overview);
//        movieList.add(movie);
//        user = new User();
//    }
//    @AfterEach
//    public void tearDown(){
//        userRepository.deleteAll();
//        movie = null;
//        user=null;
//
//    }
//
//    @Test
//    public void testAddUser() throws UserAlreadyException {
//        UserDto userDto = new UserDto(user.getName(),user.getEmail(),user.getPassword(),user.getPhoneNo(),user.getImageName());
//        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(null));
//        when(userRepository.insert(user)).thenReturn(user);
//        when(movieProxy.registerUser(userDto)).thenReturn(null);
//
//        User newUser = userService.addUser(user);
//        assertEquals(user,newUser);
//        verify(userRepository,times(1)).findById(user.getEmail());
//        verify(userRepository,times(1)).insert(user);
//    }
//    @Test
//    public void testAddUserFailure(){
////        UserDto userDto =  new UserDto(user.getName(),user.getEmail(),user.getPassword(),user.getPhoneNo(),user.getImagePath());
//        when(userRepository.findById(user.getEmail())).thenReturn(Optional.of(user));
//        assertThrows(UserAlreadyException.class,()->userService.addUser(user));
//        verify(userRepository,times(1)).findById(user.getEmail());
//        verify(userRepository,times(0)).insert(user);
//
//    }
//    @Test
//    public void testDeleteUser() throws UserException {
//        when(userRepository.findById(user.getEmail())).thenReturn(Optional.of(user));
//        movieProxy.deleteUser(user.getEmail());
//        boolean result = userService.deleteUser(user.getEmail());
//        assertTrue(result);
//        verify(userRepository,times(1)).findById(user.getEmail());
//        verify(userRepository,times(1)).findById(user.getEmail());
//    }
//    @Test
//    public void testDeleteUserFailure(){
//        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(null));
//        assertThrows(UserException.class,()->userService.deleteUser(user.getEmail()));
//        verify(userRepository,times(1)).findById(user.getEmail());
//
//    }
//    @Test
//    public void testGetAllFavouriteMovie() throws UserException {
//        when(userRepository.findById(user.getEmail())).thenReturn(Optional.of(user));
//        List<Movie> movieList = userRepository.findById(user.getEmail()).get().getFavouriteMovielist();
//
//        List<Movie> movies = userService.getAllFavouriteMovie(user.getEmail());
//        assertEquals(movies,movieList);
//        verify(userRepository,times(3)).findById(user.getEmail());
//
//    }
//    @Test
//    public void testGetAllFavouriteMovieFailure(){
//        when(userRepository.findById("harsh@gmail.com")).thenReturn(Optional.empty());
//         assertThrows(UserException.class, () -> userService.getAllFavouriteMovie("harsh@gmail.com"));
//        verify(userRepository,times(1)).findById("harsh@gmail.com");
////        when(userRepository.findById(user.getEmail())).thenReturn(Optional.ofNullable(user));
////        assertThrows(UserException.class,()->userService.getAllFavouriteMovie(user.getEmail()));
////        verify(userRepository,times(1)).findById(user.getEmail());
//
//    }
//    @Test
//    public void testDeleteMovie() throws MovieException, UserException {
//        when(userRepository.findById(user.getEmail())).thenReturn(Optional.of(user));
//        boolean isDeleted = userService.deleteFavoriteMovie(user.getEmail(),movie);
//        assertTrue(isDeleted);
//        verify(userRepository,times(1)).save(user);
//        assertEquals(0,user.getFavouriteMovielist().size());
//
//    }
//    @Test
//    public void testDeleteMovieFailure(){
//        when(userRepository.findById("harsh@gmail.com")).thenReturn(Optional.empty());
//        assertThrows(UserException.class,()->userService.deleteFavoriteMovie("harsh@gmail.com",movie));
//        verify(userRepository,times(1)).findById("harsh@gmail.com");
//        when(userRepository.findById(user.getEmail())).thenReturn(Optional.of(user));
//        Movie newmovie = new Movie(5678, "Another Movie", "/path/to/another_poster.jpg",  "This is another movie.");
//        assertThrows(MovieException.class,()->userService.deleteFavoriteMovie(user.getEmail(),newmovie));
//        verify(userRepository,times(1)).findById(user.getEmail());
//
//    }
//    @Test
//    public void testAddFavoriteMovie() throws MovieAlreadyExist, UserException {
//        when(userRepository.findById(user.getEmail())).thenReturn(Optional.of(user));
//        when(userRepository.save(user)).thenReturn(user);
//        Movie newmovie = new Movie(5678, "Another Movie", "/path/to/another_poster.jpg", "This is another movie.");
//        User updateduser = userService.addMovieToFavorite(user.getEmail(),newmovie);
//        assertEquals(2,updateduser.getFavouriteMovielist().size());
//        verify(userRepository,times(1)).findById(user.getEmail());
//        verify(userRepository,times(1)).save(updateduser);
//    }
//    @Test
//    public void testAddFavouriteFailure(){
//        when(userRepository.findById("harsh@gmail.com")).thenReturn(Optional.empty());
//        assertThrows(UserException.class,()->userService.deleteFavoriteMovie("harsh@gmail.com",movie));
//        verify(userRepository,times(1)).findById("harsh@gmail.com");
//        when(userRepository.findById(user.getEmail())).thenReturn(Optional.of(user));
//        assertThrows(MovieAlreadyExist.class,()->userService.addMovieToFavorite(user.getEmail(),movie));
//        verify(userRepository,times(1)).findById(user.getEmail());
//    }
//}
