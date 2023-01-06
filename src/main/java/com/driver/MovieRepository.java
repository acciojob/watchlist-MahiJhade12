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
                pairOfMovieDirector.put(director, currMovies);
            }
        }
    }
    public Movie findmovie(String name){
        return movieHashMap.get(name);
    }
    public  Director findDirector(String name){
        return directorHashMap.get(name);
    }

    public List<String> getAllMovieByDirectorName(String name){
        if(pairOfMovieDirector.containsKey(name)) {
            return pairOfMovieDirector.get(name);
        }
        return null;
    }
    public List<String> getAllMovie(){
        return new ArrayList<>(movieHashMap.keySet());
    }
    public void deleteDiector(String name){

        if(pairOfMovieDirector.containsKey(name)) {
            List<String> currList=new ArrayList<>();
            currList=pairOfMovieDirector.get(name);
            for(int i=0;i<currList.size();i++){
                if(movieHashMap.containsKey(currList.get(i))){
                    movieHashMap.remove(currList.get(i));
                }
                pairOfMovieDirector.remove(name);
            }
        }
        if (directorHashMap.containsKey(name)){
            directorHashMap.remove(name);
        }

    }
    public void  deleteAllDirectorMovie(){
       HashSet<String> movieSet=new HashSet<>();
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
    }

}