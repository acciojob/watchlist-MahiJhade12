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

   @PostMapping("/add_Director")
    public  ResponseEntity<String> addDirector(@RequestBody() Director director ){
       movieService.addDirector(director);
       return new ResponseEntity<>("director added succesfully",HttpStatus.CREATED);
   }

    @PutMapping("/add_movie_director_pair")
    public ResponseEntity<String> addDirectorMovePair(@RequestBody() String movie ,String director){
       movieService.addDirectorMovePair(movie,director);
       return  new ResponseEntity<>("movie_director pair added",HttpStatus.CREATED);
    }

    @GetMapping("/get_movie")
    public ResponseEntity<Movie> getMovieByName(@RequestParam("movie_name") String movie_name){
      Movie movie=movieService.getMovieByName(movie_name);
       return new ResponseEntity<>(movie,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_director")
    public  ResponseEntity<Director> getDirectorByName(@RequestParam("director_name") String director_name){
       Director director=movieService.getDirectorByName(director_name);
       return new ResponseEntity<>(director,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_movieList_byDirectorName")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@RequestParam("director_name") String director_name){
       List<String> movieList=movieService.getMovieListByDirectorName(director_name);
       return new ResponseEntity<>(movieList,HttpStatus.ACCEPTED);
    }

    @GetMapping("/find_all_movies")
    public ResponseEntity<List<String>> findAllMovies() {
        List<String> movieList = movieService.findAllMovies();
        return new ResponseEntity<>(movieList,HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete_director")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director_name") String director_name){
        movieService.deleteDirector(director_name);
       return new ResponseEntity<>("director deleted",HttpStatus.ACCEPTED);
    }
   @DeleteMapping("/delete_all_director")
    public  ResponseEntity<String> deleteAllDirectors(){
       movieService.deleteAllDirectorMovie();
       return new ResponseEntity<>("All director_movie deleted",HttpStatus.ACCEPTED);
   }
}
