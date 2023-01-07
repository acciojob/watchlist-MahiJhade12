package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

   private HashMap<String, Movie> movieHashMap;
   private HashMap<String, Director> directorHashMap;
   private HashMap<String, List<String>> pairOfMovieDirector;

    public MovieRepository(){
        this.movieHashMap = new HashMap<String, Movie>();
        this.directorHashMap = new HashMap<String, Director>();
        this.pairOfMovieDirector = new HashMap<String, List<String>>();
    }
    public void saveMovie(Movie movie) {
        movieHashMap.put(movie.getName(), movie);
    }

    public void saveDirector(Director director) {
        directorHashMap.put(director.getName(), director);
    }

    public void saveDirectorMovePair(String movie, String director) {
        if (movieHashMap.containsKey(movie) && directorHashMap.containsKey(director)) {
            List<String> currMovies = new ArrayList<>();
            if (pairOfMovieDirector.containsKey(director))
                currMovies = pairOfMovieDirector.get(director);
                currMovies.add(movie);
            pairOfMovieDirector.put(director, currMovies);
        }
    }

    public Movie findmovie(String movie_name){
        return movieHashMap.get(movie_name);
    }

    public  Director findDirector(String director_name){
        return directorHashMap.get(director_name);
    }

    public List<String> getAllMovieByDirectorName(String director_name){
        List<String> moviesList = new ArrayList<String>();
        if(pairOfMovieDirector.containsKey(director_name))
            moviesList = pairOfMovieDirector.get(director_name);
        return moviesList;
    }
    public List<String> getAllMovie(){
        return new ArrayList<>(movieHashMap.keySet());
    }

  public void deleteDiector(String director){

      List<String> movies = new ArrayList<String>();
      if(pairOfMovieDirector.containsKey(director)){
          movies = pairOfMovieDirector.get(director);
          for(String movie: movies){
              if(movieHashMap.containsKey(movie)){
                  movieHashMap.remove(movie);
              }
          }
          pairOfMovieDirector.remove(director);
      }
      if(directorHashMap.containsKey(director)){
          directorHashMap.remove(director);
      }
  }
    public void deleteAllDirectorMovie(){

        HashSet<String> moviesSet = new HashSet<String>();

        //Deleting the director's map
        directorHashMap = new HashMap<>();

        //Finding out all the movies by all the directors combined
        for(String director: pairOfMovieDirector.keySet()){

            //Iterating in the list of movies by a director.
            for(String movie: pairOfMovieDirector.get(director)){
                moviesSet.add(movie);
            }
        }

        //Deleting the movie from the movieDb.
        for(String movie: moviesSet){
            if(movieHashMap.containsKey(movie)){
                movieHashMap.remove(movie);
            }
        }
        //clearing the pair.
        pairOfMovieDirector = new HashMap<>();
    }

    public String findDirectorByMovieName(String movie_name){

        for(String director: pairOfMovieDirector.keySet()){
            //Iterating in the list of movies by a director.
            HashSet<String> moviesSet = new HashSet<String>();
            for(String movie: pairOfMovieDirector.get(director)){
                moviesSet.add(movie);
                if (moviesSet.contains(movie_name)){
                    return director;
                  }
            }
        }
        return "director does not exist";
    }
}
