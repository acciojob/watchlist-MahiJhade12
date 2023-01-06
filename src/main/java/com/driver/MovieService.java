package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie){
       movieRepository.saveMovie(movie);
    }

   public  void  addDirector(Director director){
        movieRepository.saveDirector(director);
   }
    public  void addDirectorMovePair(String movie,String director){
        movieRepository.saveDirectorMovePair(movie,director);
    }
    public  Movie getMovieByName(String movie_name){
       return movieRepository.findmovie(movie_name);
    }
    public  Director getDirectorByName(String director_name){
      return   movieRepository.findDirector(director_name);
    }
    public List<String> getMovieListByDirectorName(String director){
        return  movieRepository.getAllMovieByDirectorName(director);
    }
    public  List<String> findAllMovies(){
        return movieRepository.getAllMovie();
    }
    public void  deleteDirector(String Director_name){
        movieRepository.deleteDiector(Director_name);
    }
    public void  deleteAllDirectorMovie(){
        movieRepository.deleteAllDirectorMovie();
    }
}
