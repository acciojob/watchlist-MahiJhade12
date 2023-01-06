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
  /*  public void deleteDiector(String director_name){
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

    }*/
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
  public void deleteDiector(String director){

      List<String> movies = new ArrayList<String>();
      if(pairOfMovieDirector.containsKey(director)){
          //1. Find the movie names by director from the pair
          movies = pairOfMovieDirector.get(director);

          //2. Deleting all the movies from movieDb by using movieName
          for(String movie: movies){
              if(movieHashMap.containsKey(movie)){
                  movieHashMap.remove(movie);
              }
          }

          //3. Deleteing the pair
          pairOfMovieDirector.remove(director);
      }

      //4. Delete the director from directorDb.
      if(directorHashMap.containsKey(director)){
          directorHashMap.remove(director);
      }
  }

}
/*
@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    //Pair is : DirectorName, List of Movie Names


    //Initialization is very important :

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    public void saveMovie(Movie movie){
        movieMap.put(movie.getName(), movie);
    }

    public void saveDirector(Director director){
        directorMap.put(director.getName(), director);
    }

    public void saveDirectorMovePair(String movie, String director){

        //1. Add the movie into Datbase ---> WRONG bcz I dont have te movie object

        if(movieMap.containsKey(movie)&&directorMap.containsKey(director)){

            List<String> currentMoviesByDirector = new ArrayList<>();

            if(directorMovieMapping.containsKey(director))
                currentMoviesByDirector = directorMovieMapping.get(director);

            currentMoviesByDirector.add(movie);

            directorMovieMapping.put(director,currentMoviesByDirector);

        }

    }

    public Movie findmovie(String movie){
        return movieMap.get(movie);
    }

    public Director findDirector(String director){
        return directorMap.get(director);
    }

    public List<String> getAllMovieByDirectorName(String director){
        List<String> moviesList = new ArrayList<String>();
        if(directorMovieMapping.containsKey(director))
            moviesList = directorMovieMapping.get(director);
        return moviesList;
    }

    public List<String> getAllMovie(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDiector(String director){

        List<String> movies = new ArrayList<String>();
        if(directorMovieMapping.containsKey(director)){
            //1. Find the movie names by director from the pair
            movies = directorMovieMapping.get(director);

            //2. Deleting all the movies from movieDb by using movieName
            for(String movie: movies){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }

            //3. Deleteing the pair
            directorMovieMapping.remove(director);
        }

        //4. Delete the director from directorDb.
        if(directorMap.containsKey(director)){
            directorMap.remove(director);
        }
    }

    public void deleteAllDirectorMovie(){

        HashSet<String> moviesSet = new HashSet<String>();

        //Deleting the director's map
        directorMap = new HashMap<>();

        //Finding out all the movies by all the directors combined
        for(String director: directorMovieMapping.keySet()){

            //Iterating in the list of movies by a director.
            for(String movie: directorMovieMapping.get(director)){
                moviesSet.add(movie);
            }
        }

        //Deleting the movie from the movieDb.
        for(String movie: moviesSet){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
        //clearing the pair.
        directorMovieMapping = new HashMap<>();
    }
}*/