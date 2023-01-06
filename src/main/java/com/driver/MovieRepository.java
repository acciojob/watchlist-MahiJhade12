package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class MovieRepository {

    HashMap<String, Movie> movieHashMap;
    HashMap<String, Director> directorHashMap;
    HashMap<String, List<String>> pairOfMovieDirector;

    public MovieRepository() {
        this.movieHashMap = new HashMap<>();
        this.directorHashMap = new HashMap<>();
        this.pairOfMovieDirector = new HashMap<>();
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
            if (pairOfMovieDirector.containsKey(director)) {
                currMovies = pairOfMovieDirector.get(director);
                currMovies.add(movie);
            }
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
    public void deleteDiector(String director_name){
        List<String> currList=new ArrayList<>();
        if(pairOfMovieDirector.containsKey(director_name)) {
            currList=pairOfMovieDirector.get(director_name);

            for(String movie: currList){
                if(movieHashMap.containsKey(movie)){
                    movieHashMap.remove(movie);
                }
            }
                pairOfMovieDirector.remove(director_name);
            }
        if (directorHashMap.containsKey(director_name)){
            directorHashMap.remove(director_name);
        }

    }
    public void  deleteAllDirectorMovie(){
       HashSet<String> movieSet=new HashSet<>();
       directorHashMap = new HashMap<>();
       for(String director:pairOfMovieDirector.keySet()){
           for(String movie:pairOfMovieDirector.get(director)){
             movieSet.add(movie);
           }
       }
       for (String movie:movieSet){
           if(movieHashMap.containsKey(movie)){
               movieHashMap.remove(movie);
           }
       }
        pairOfMovieDirector = new HashMap<>();
    }

}

