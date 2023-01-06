package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("movies")
public class MovieController {
   @Autowired
    MovieService movieService;

   @PostMapping("/add_movie")
    public ResponseEntity<String> addMovie(@RequestBody() Movie movie){
       movieService.addMovie(movie);
       return new ResponseEntity<>("New movie added succesfully",HttpStatus.CREATED);
   }

   @PostMapping("/add_director")
    public  ResponseEntity<String> addDirector(@RequestBody() Director director ){
       movieService.addDirector(director);
       return new ResponseEntity<>("director added succesfully",HttpStatus.CREATED);
   }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movie ,@RequestParam("director") String director){
       movieService.addDirectorMovePair(movie,director);
       return  new ResponseEntity<>("movie_director pair added",HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable  String movie_name){
      Movie movie=movieService.getMovieByName(movie_name);
       return new ResponseEntity<>(movie,HttpStatus.CREATED);
    }

    @GetMapping("/get-director-by-name/{name}")
    public  ResponseEntity<Director> getDirectorByName(@PathVariable String director_name){
       Director director=movieService.getDirectorByName(director_name);
       return new ResponseEntity<>(director,HttpStatus.CREATED);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable  String director_name){
       List<String> movieList=movieService.getMovieListByDirectorName(director_name);
       return new ResponseEntity<>(movieList,HttpStatus.CREATED);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {
        List<String> movieList = movieService.findAllMovies();
        return new ResponseEntity<>(movieList,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director_name") String director_name){
        movieService.deleteDirector(director_name);
       return new ResponseEntity<>(director_name+"removed successfully",HttpStatus.CREATED);
    }
   @DeleteMapping("/delete-all-directors")
    public  ResponseEntity<String> deleteAllDirectors(){
       movieService.deleteAllDirectorMovie();
       return new ResponseEntity<>("All director_movie deleted",HttpStatus.CREATED);
   }
}


