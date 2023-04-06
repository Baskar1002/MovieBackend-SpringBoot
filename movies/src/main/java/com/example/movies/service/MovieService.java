package com.example.movies.service;

import java.util.List;
import com.example.movies.validation.MovieValidator;


import org.springframework.beans.factory.annotation.Autowired;

import com.example.movies.model.Movie;
import com.example.movies.repository.MovieRepository;

@SuppressWarnings("unused")
public class MovieService {
	
	@Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

      public void saveMovies(List<Movie> movies) {
        movieRepository.saveAll(movies);
      }
        public Movie createMovie(Movie movie) {
            return movieRepository.save(movie);
      }

    }

