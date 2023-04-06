package com.example.movies.model;

import jakarta.persistence.Column;
import com.example.movies.validation.MovieValidator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


//p

@SuppressWarnings("unused")
@Entity
@Table(name = "movies")
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true) // this ensures that title values are unique(name = "title")
    private String title;
    
    @Column(unique = true) // this ensures that title values are unique(name = "short_title")
    private String shortTitle;
    
    @Column(name = "distributor")
    private String distributor;
    
    @Column(name = "territory")
    private String territory;
    
    @Column(name = "primary_genre")
    private String primaryGenre;
    
    @Column(name = "additional_genre")
    private String additionalGenre;
    
    @Column(name = "director_1")
    private String director1;
    
    @Column(name = "director_2")
    private String director2;
    
    @Column(name = "cast_1")
    private String cast1;
    
    @Column(name = "cast_2")
    private String cast2;
    
    @Column(name = "synopsis")
    private String synopsis;
    
    public Movie() {}
    
    public Movie(String title, String shortTitle, String distributor, String territory, String primaryGenre, String additionalGenre, String director1, String director2, String cast1, String cast2, String synopsis) {
        this.title = title;
        this.shortTitle = shortTitle;
        this.distributor = distributor;
        this.territory = territory;
        this.primaryGenre = primaryGenre;
        this.additionalGenre = additionalGenre;
        this.director1 = director1;
        this.director2 = director2;
        this.cast1 = cast1;
        this.cast2 = cast2;
        this.synopsis = synopsis;
    }
    
    
    //getter setter 
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getShortTitle() {
        return shortTitle;
    }
    
    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }
    
    public String getDistributor() {
        return distributor;
    }
    
    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }
    
    public String getTerritory() {
        return territory;
    }
    
    public void setTerritory(String territory) {
        this.territory = territory;
    }
    
    public String getPrimaryGenre() {
        return primaryGenre;
    }
    
    public void setPrimaryGenre(String primaryGenre) {
        this.primaryGenre = primaryGenre;
    }
    
    public String getAdditionalGenre() {
        return additionalGenre;
    }
    
    public void setAdditionalGenre(String additionalGenre) {
        this.additionalGenre = additionalGenre;
    }
    
    public String getDirector1() {
        return director1;
    }
    
    public void setDirector1(String director1) {
        this.director1 = director1;
    }
    
    public String getDirector2() {
        return director2;
    }
    
    public void setDirector2(String director2) {
        this.director2 = director2;
    }
    
    public String getCast1() {
        return cast1;
    }
    
    public void setCast1(String cast1) {
        this.cast1 = cast1;
    }
    
    public String getCast2() {
        return cast2;
    }
    
    public void setCast2(String cast2) {
        this.cast2 = cast2;
    }

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis ;
		
		
		
	}

	public String getSynopsis() {		
		return synopsis ;
	}

	public static Object deleteAll() {
		// TODO Auto-generated method stub
		return null ;
		
		
	}

	public static boolean deleteById(Long id2) {
		// TODO Auto-generated method stub
		return false;
	}
    
}

    




