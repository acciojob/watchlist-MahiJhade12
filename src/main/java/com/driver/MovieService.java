package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
/*
@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie){
        movieRepository.saveMovie(movie);
    }

    public void addDirector(Director director){
        movieRepository.saveDirector(director);
    }

    public void createMovieDirectorPair(String movie, String director){
        movieRepository.saveMovieDirectorPair(movie, director);
    }

    public Movie findMovie(String movieName){
        return movieRepository.findMovie(movieName);
    }

    public Director findDirector(String directorName){
        return movieRepository.findDirector(directorName);
    }

    public List<String> findMoviesFromDirector(String director){
        return movieRepository.findMoviesFromDirector(director);
    }

    public List<String> findAllMovies(){
        return movieRepository.findAllMovies();
    }

    public void deleteDirector(String director){
        movieRepository.deleteDirector(director);
    }

    public void deleteAllDirectors(){
        movieRepository.deleteAllDirector();
    }
}*/