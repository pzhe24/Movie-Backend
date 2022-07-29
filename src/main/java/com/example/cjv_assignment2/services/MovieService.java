package com.example.cjv_assignment2.services;


import com.example.cjv_assignment2.models.Movie;
import com.example.cjv_assignment2.models.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {
    //all functions and logic go in the service class
    @Autowired
    private MovieRepository repository;

    @Autowired
    private MongoTemplate template;

    //create a movie/tv show
    public void insertIntoVideo(Movie video){
        repository.insert(video);
    }

    //Query for getting all the movies
    public List<Movie> getMovies(){
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is("movie"));
        return template.find(query, Movie.class);
    }
    //query for getting all the tv shows
    public List<Movie> getTV(){
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is("tv"));
        return template.find(query, Movie.class);
    }

    //getting all the movies / tv shows with a certain title
    public List<Movie> getByTitle(String title){
        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex(title, "i"));
        return template.find(query, Movie.class);
    }

    //getting all the featured Movies
    public List<Movie> getFeaturedMovies(String feature){
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is("movie").and("isFeatured").is(feature));
        return template.find(query, Movie.class);
    }

    //getting all the featured TV shows
    public List<Movie> getFeaturedTV(String feature){
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is("tv").and("isFeatured").is(feature));
        return template.find(query, Movie.class);
    }

    //getting a movie/tv show by ID
    public Optional<Movie> getAVideo(String id) throws Exception {

        Optional<Movie> video = repository.findById(id);

        if (!video.isPresent()) {
            throw new Exception("Video with id: " + id + " Does Not Exist");
        }
        return video;
    }

    public Movie updateMovie(String id, Movie updatedMovie) throws Exception {
        Optional<Movie> movie = repository.findById(id);

        if(!movie.isPresent()){
            throw new Exception("Movie with id " + id + " Does not Exist");
        }
        //check if title has a value
        if(!updatedMovie.getTitle().isBlank()){
            movie.get().setTitle(updatedMovie.getTitle());
        }
        if(!updatedMovie.getYear().isBlank()){
            movie.get().setYear(updatedMovie.getYear());
        }
        if(!updatedMovie.getDescription().isBlank()){
            movie.get().setDescription(updatedMovie.getDescription());
        }
        if(!updatedMovie.getGenres().isBlank()){
            movie.get().setGenres(updatedMovie.getGenres());
        }
        if(!updatedMovie.getRuntime().isBlank()){
            movie.get().setRuntime(updatedMovie.getRuntime());
        }
        if(!updatedMovie.getPoster().isBlank()){
            movie.get().setPoster(updatedMovie.getPoster());
        }
        if(!updatedMovie.getRentPrice().isBlank()){
            movie.get().setRentPrice(updatedMovie.getRentPrice());
        }
        if(!updatedMovie.getBuyPrice().isBlank()){
            movie.get().setBuyPrice(updatedMovie.getBuyPrice());
        }
        if(!updatedMovie.getIsFeatured().isBlank()){
            movie.get().setIsFeatured(updatedMovie.getIsFeatured());
        }
        if(!updatedMovie.getType().isBlank()){
            movie.get().setType(updatedMovie.getType());
        }
        Movie editedMovie = repository.save(movie.get());
        return editedMovie;
    }

    public Optional<Movie> deleteAVideo(String id) throws Exception {
        Optional<Movie> video = repository.findById(id);

        if(!video.isPresent()){
            throw new Exception("Video with id: "+ id + " Does Not Exist");
        }
        repository.deleteById(id);
        return video;
    }
}
