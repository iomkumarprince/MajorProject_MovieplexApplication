package com.MovieApp.Movie.App.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie {
   private int id;
   private String title;
   private String poster_path;
   private String overview;

}
