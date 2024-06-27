//package com.MovieApp.Movie.App;
//
//import com.MovieApp.Movie.App.controller.UserMovieController;
//import com.MovieApp.Movie.App.domain.Movie;
//import com.MovieApp.Movie.App.domain.User;
//import com.MovieApp.Movie.App.exception.MovieAlreadyExist;
//import com.MovieApp.Movie.App.exception.MovieException;
//import com.MovieApp.Movie.App.exception.UserAlreadyException;
//import com.MovieApp.Movie.App.exception.UserException;
//import com.MovieApp.Movie.App.service.UserServiceImpl;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@ExtendWith(MockitoExtension.class)
//public class MovieControllerTest {
//  @Mock
//  private UserServiceImpl userService;
//  @InjectMocks
//  private UserMovieController movieController;
//  @Autowired
//  private MockMvc mockMvc;
//  private User user;
//  private Movie movie;
//
//  @BeforeEach
//    public void tearUp(){
//      int movieId = 1234;
//      String originalTitle = "Example Movie";
//      String posterPath = "/path/to/poster.jpg";
//      String overview = "This is an example movie.";
//      List<Movie> movieList = new ArrayList<>();
//
//      movie = new Movie(movieId, originalTitle, posterPath, overview);
//      movieList.add(movie);
//      user = new User("John Doe", "johndoe@example.com", "password123",
//              "1234567890", "/path/to/image.jpg", movieList);
//      mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
//  }
//  @AfterEach
//  public void tearDown(){
//    user=null;
//    movie=null;
//  }
//public static String convertToJson(final Object object) {
//    String result = "";
//    ObjectMapper mapper = new ObjectMapper();
//    try {
//        result = mapper.writeValueAsString(object);
//
//    } catch (JsonProcessingException e) {
//        e.printStackTrace();
//    }
//    return result;
// }
//    @Test
//    public void getAllMoviesSuccess() throws Exception{
//        when(userService.getAllFavouriteMovie(user.getEmail())).thenReturn(List.of(movie));
//        mockMvc.perform(
//                        get("/movie/api/v1/getFavouriteMovies")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .requestAttr("email", user.getEmail())
//                                .content(convertToJson(user)))
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(userService,times(1)).getAllFavouriteMovie(user.getEmail());
//    }
//    @Test
//    public void getAllMoviesFailure() throws Exception {
//      when(userService.getAllFavouriteMovie(user.getEmail())).thenThrow(UserException.class);
//        mockMvc.perform(
//                        get("/movie/api/v1/getFavouriteMovies")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .requestAttr("email", user.getEmail())
//                                .content(convertToJson(user)))
//                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
//        verify(userService,times(1)).getAllFavouriteMovie(user.getEmail());
//    }
//    @Test
//    public void testDeleteMovieSuccess() throws Exception {
//      when(userService.deleteFavoriteMovie(user.getEmail(),movie)).thenReturn(true);
//        mockMvc.perform(
//                        post("/movie/api/v1/deleteMovie")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .requestAttr("email", user.getEmail())
//                                .content(convertToJson(movie)))
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(userService,times(1)).deleteFavoriteMovie(user.getEmail(),movie);
//    }
//    @Test
//    public void testDeleteMovieFailure() throws Exception {
//        when(userService.deleteFavoriteMovie(user.getEmail(),movie)).thenThrow(MovieException.class);
//        mockMvc.perform(
//                        post("/movie/api/v1/deleteMovie")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .requestAttr("email", user.getEmail())
//                                .content(convertToJson(movie)))
//                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
//        verify(userService,times(1)).deleteFavoriteMovie(user.getEmail(),movie);
//    }
//@Test
//public void testAddMovieSuccess() throws Exception {
//      when(userService.addMovieToFavorite(user.getEmail(),movie)).thenReturn(user);
//    mockMvc.perform(
//                    post("/movie/api/v1/addMovie")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .requestAttr("email", user.getEmail())
//                            .content(convertToJson(movie)))
//            .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//    verify(userService,times(1)).addMovieToFavorite(user.getEmail(),movie);
//}
//@Test
//public void testAddMovieFailure() throws Exception {
//    when(userService.addMovieToFavorite(user.getEmail(),movie)).thenThrow(MovieAlreadyExist.class);
//    mockMvc.perform(
//                    post("/movie/api/v1/addMovie")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .requestAttr("email", user.getEmail())
//                            .content(convertToJson(movie)))
//            .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
//    verify(userService,times(1)).addMovieToFavorite(user.getEmail(),movie);
//}
//
// @Test
//    public void addUserSuccess() throws Exception {
//      when(userService.addUser(user)).thenReturn(user);
//      mockMvc.perform(
//              post("/movie/api/v1/addUser")
//                      .contentType(MediaType.APPLICATION_JSON)
//                      .content(convertToJson(user)))
//                      .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//     verify(userService,times(1)).addUser(user);
// }
//
// @Test
//    public void addUserFailure()throws Exception{
//     when(userService.addUser(user)).thenThrow(UserAlreadyException.class);
//     mockMvc.perform(
//                     post("/movie/api/v1/addUser")
//                             .contentType(MediaType.APPLICATION_JSON)
//                             .content(convertToJson(user)))
//             .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
//     verify(userService,times(1)).addUser(user);
// }
// @Test
//    public void deleteUserSuccess() throws Exception {
//     when(userService.deleteUser(user.getEmail())).thenReturn(true);
//     mockMvc.perform(
//                     delete("/movie/api/v1/deleteUser")
//                             .contentType(MediaType.APPLICATION_JSON)
//                             .requestAttr("email", user.getEmail()))
//             .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//     verify(userService,times(1)).deleteUser(user.getEmail());
// }
// @Test
//    public void deleteUserFailure() throws Exception {
//      when(userService.deleteUser(user.getEmail())).thenThrow(UserException.class);
//      mockMvc.perform(
//                      delete("/movie/api/v1/deleteUser")
//                              .contentType(MediaType.APPLICATION_JSON)
//                              .requestAttr("email", user.getEmail()))
//              .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
//     verify(userService,times(1)).deleteUser(user.getEmail());
// }
//
//}
