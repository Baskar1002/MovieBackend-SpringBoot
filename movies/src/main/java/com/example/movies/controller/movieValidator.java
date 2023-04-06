package com.example.movies.controller;
 

import org.springframework.stereotype.Component;
import com.example.movies.validation.MovieValidator;

import com.example.movies.model.Movie;

@SuppressWarnings("unused")
@Component

public class movieValidator {

    public void validate(Movie movie) throws ValidationException {
    	
    	if (movie.getTitle() == null || movie.getTitle().isEmpty()) {
            throw new ValidationException("Title cannot be empty");
        }
        if (movie.getShortTitle() == null || movie.getShortTitle().isEmpty()) {
            throw new ValidationException("Short title cannot be empty");
        }
    	
    	
        if (!isValidAlphaNumeric(movie.getTitle())) {
            throw new ValidationException("Invalid title: " + movie.getTitle());
        }
        if (!isValidAlphaNumeric(movie.getShortTitle())) {
            throw new ValidationException("Invalid short title: " + movie.getShortTitle());
        }
        if (!isValidAlphaNumeric(movie.getDirector1())) {
            throw new ValidationException("Invalid director1: " + movie.getDirector1());
        }
        if (!isValidAlphaNumeric(movie.getDirector2())) {
            throw new ValidationException("Invalid director2: " + movie.getDirector2());
        }
        if (!isValidAlphaNumeric(movie.getSynopsis())) {
            throw new ValidationException("Invalid synopsis: " + movie.getSynopsis());
        }
        if (!isValidAlphaNumeric(movie.getTerritory())) {
                throw new ValidationException("Invalid territory: " + movie.getTerritory());
            }
        
        if (movie.getTerritory() != null && !isValidAlphaNumeric(movie.getTerritory())) {
            throw new ValidationException("Invalid territory: " + movie.getTerritory());
        }
        
        if (movie.getShortTitle().length() < 4 || movie.getShortTitle().length() > 6) {
            throw new ValidationException("Short title must be between 4 to 6 characters");
        }
        
        if (movie.getAdditionalGenre() == null) {
            movie.setAdditionalGenre("Action");
        }

        
        }
    

    private boolean isValidAlphaNumeric(String s) {
        return s.matches("^[a-zA-Z0-9]+$");
    }

    public static class ValidationException extends Exception {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 *
		private static final long serialVersionUID = 1L;

		/**
		 * 
		 */
		

		public ValidationException(String message) {
            super(message);
        }
    }
}





