package com.example.movies.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.example.movies.model.Movie;
import com.example.movies.validation.MovieValidator;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@SuppressWarnings("unused")




	@Repository
	public interface MovieRepository extends JpaRepository<Movie, Long> {

	    @Query("SELECT m.title, m.shortTitle, m.distributor, m.territory, m.primaryGenre, m.additionalGenre, m.director1, m.director2, m.cast1, m.cast2, m.synopsis FROM Movie m")
	    List<Object[]> getMovieDetails();
	    
	    // delete all movies from the database
	    
	    // @Modifying
	    //@Query("delete from Movie")
	    //void deleteAllMovies();
	    //void deleteMovieById();

		Object findByShortTitle(String shortTitle);

		Optional<Movie> findByTitle(String title);

		Movie findByTitleOrShortTitle(String title, String shortTitle);

	}


	

	




	

