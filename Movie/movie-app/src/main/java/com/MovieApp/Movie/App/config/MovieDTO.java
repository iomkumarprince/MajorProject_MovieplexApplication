package com.MovieApp.Movie.App.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.json.simple.JSONObject;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class MovieDTO {
    private JSONObject jsonObject;
}
