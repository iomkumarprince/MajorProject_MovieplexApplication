//package com.MovieApp.Movie.App;
//
//
//import com.MovieApp.Movie.App.domain.Movie;
//import com.MovieApp.Movie.App.domain.User;
//import com.MovieApp.Movie.App.repository.UserRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//@ExtendWith(SpringExtension.class)
//@DataMongoTest
//public class MovieRepoTest {
//    @Autowired
//    private UserRepository userRepository;
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
//        user = new User("John Doe", "johndoe@example.com", "password123",
//                "1234567890", "/path/to/image.jpg", movieList);
//    }
//    @AfterEach
//    public void tearDown(){
//        movie = null;
//        user=null;
//        userRepository.deleteAll();
//    }
//    @Test
//    public void testInsertUser(){
//        User insertedUser = userRepository.save(user);
//        assertEquals(insertedUser,user);
//    }
//    @Test
//    public void testDeleteUser(){
//        userRepository.insert(user);
//        User user1 = userRepository.findById(user.getEmail()).get();
//        userRepository.delete(user1);
//        assertEquals(false,userRepository.findById(user.getEmail()).isPresent());
//    }
//    @Test
//    public void testGetAllTrack(){
//        userRepository.save(user);
//        user.setEmail("123@gmial.com");
//        userRepository.save(user);
//        user.setEmail("456@gmail.com");
//        userRepository.save(user);
//        List<User> userList = userRepository.findAll();
//        assertEquals(3,userList.size());
//        assertNotEquals(true,userList.isEmpty());
//    }
//
//}
