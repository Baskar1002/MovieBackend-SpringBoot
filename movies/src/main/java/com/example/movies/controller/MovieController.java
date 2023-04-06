package com.example.movies.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
//import org.apache.commons.io.FileUtils;


import com.example.movies.model.Movie;
import com.example.movies.repository.MovieRepository;


@SuppressWarnings("unused")
@RestController
@RequestMapping("/api")
@CrossOrigin(maxAge = 3600)
public class MovieController {

    private static final String errorFile = null;
	@Autowired
    private MovieRepository movieRepository;   
	
    @PostMapping("/movie")
    public Movie create(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @GetMapping("/movie")
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }     
                @SuppressWarnings("null")
				@PostMapping("/upload")
                public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
                    try {
                        // Create a new file in the server's temporary directory
                        File newFile = File.createTempFile("movies", ".xlsx");

                        // Write the uploaded file's contents to the new file
                        byte[] bytes = file.getBytes();
                        OutputStream os = new FileOutputStream(newFile);
                        os.write(bytes);
                        os.close();

                        // Read the contents of the new file into a POI workbook
                        Workbook workbook = WorkbookFactory.create(newFile);

                        // Open the first sheet of the workbook
                        Sheet sheet = workbook.getSheetAt(0);

                        // Create a set to store existing titles and short titles
                        Set<String> existingTitles = new HashSet<>();
                        Set<String> existingShortTitles = new HashSet<>();

                        // Create a list to store the validated movies
                        List<Movie> validatedMovies = new ArrayList<>();

                        // Iterate over the rows of the sheet
                        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                            Row row = sheet.getRow(i);
                            if (row == null) {
                                continue;
                            }

                            // Parse the row's cells into a new Movie object
                            Movie movie = new Movie();
                            movie.setTitle(getAlphanumericValue(row.getCell(0)));
                            movie.setShortTitle(getAlphanumericValue(row.getCell(1)));
                            movie.setDistributor(getStringValue(row.getCell(2)));
                            movie.setTerritory(getAlphanumericValue(row.getCell(3)));
                            movie.setPrimaryGenre(getStringValue(row.getCell(4)));
                            movie.setAdditionalGenre(getStringValue(row.getCell(5)));
                            movie.setDirector1(getAlphanumericValue(row.getCell(6)));
                            movie.setDirector2(getAlphanumericValue(row.getCell(7)));
                            movie.setCast1(getStringValue(row.getCell(8)));
                            movie.setCast2(getStringValue(row.getCell(9)));
                            movie.setSynopsis(getAlphanumericValue(row.getCell(10)));

                            
                            
                            // Validate the new Movie object
                            if (!isValidTitle(movie.getTitle())) {
                                return new ResponseEntity<>("Title validation failed for Only alpha numeric are allowed for Title " +  (i+1)  + " st Record ", HttpStatus.BAD_REQUEST);
                            }
                            if (existingTitles.contains(movie.getTitle())) {
                                return new ResponseEntity<>("Existing data found " + (i+1) + " st Record ", HttpStatus.BAD_REQUEST);
                            }
                            if (!isValidShortTitle(movie.getShortTitle())) {
                                return new ResponseEntity<>("Short Title validation failed for Short Title " + (i+1)  + " st Record ", HttpStatus.BAD_REQUEST);
                            }
                            if (existingShortTitles.contains(movie.getShortTitle())) {
                                return new ResponseEntity<>("Short Title validation failed exisiting data is found for " +(i+1)  + " st Record ", HttpStatus.BAD_REQUEST);
                            }
                            if (!isValidDirector(movie.getDirector1())) {
                                return new ResponseEntity<>("Director 1 validation failed for " +(i+1) + " st Record ", HttpStatus.BAD_REQUEST);
                            }
                            if (!isValidDirector(movie.getDirector2())) {
                                return new ResponseEntity<>("Director 2 validation failed for " +(i+1)  + " st Record ", HttpStatus.BAD_REQUEST);
                            }
                            if (!isValidSynopsis(movie.getSynopsis())) {
                                return new ResponseEntity<>("Synopsis validation failed for . Only alpha numeric are allowed for Synopsis. " + (i+1)  + " st Record ", HttpStatus.BAD_REQUEST);
                            }

                            // Add the validated movie to the list of validated movies
                            validatedMovies.add(movie);    
                            
                            
//                            HttpHeaders movies = null;
//       						int j = 0;
//       						// Check if all movies are validated
//                               if (j == movies.size() - 1) {
//                                   // Return success message if all validations pass
//                                   return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
//                               }
                               
                // Save the new Movie object to the database and update the set of existing titles and short titles
                movieRepository.save(movie);
                //existingTitles.add(movie.getTitle());
               // existingShortTitles.add(movie.getShortTitle());
            }
                   

           
            // Delete the temporary file
            //newFile.delete();      

                       // Return a response entity with a status of 200 (OK)
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            // Return a response entity with the error message and a status of 500 (Internal Server Error)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while uploading file: " + e.getMessage());
        }
    }
   
    private String getAlphanumericValue(Cell cell) {
    if (cell == null) {
    return "";
    }
    String value = cell.toString();
    return value.replaceAll("[^A-Za-z0-9 ]", "");
    }    
    private String getStringValue(Cell cell) {
    if (cell == null) {
    return "";
    }
    return cell.toString();
    }    
       private boolean isValidTitle(String title) {
    return title != null && !title.trim().isEmpty();
    }      
    private boolean isValidShortTitle(String shortTitle) {
    return shortTitle != null && !shortTitle.trim().isEmpty();
    }  
       private boolean isValidDirector(String director) {
    return director != null && !director.trim().isEmpty();
    }
   
    private boolean isValidSynopsis(String synopsis) {
    return synopsis != null && !synopsis.trim().isEmpty();
    }   
    
        }