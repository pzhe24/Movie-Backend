package com.example.cjv_assignment2.controllers;

import com.example.cjv_assignment2.CustomizedResponse;
import com.example.cjv_assignment2.models.Movie;
import com.example.cjv_assignment2.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = {"http://localhost:3000", "https://cjv-a1.herokuapp.com"})
@RestController
public class MovieController {

    @Autowired
    private MovieService service;

    //adding movie/tv shows
    @PostMapping(value="/videos", consumes={
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addVideo(@RequestBody Movie video){

        service.insertIntoVideo(video);
        return new ResponseEntity(video, HttpStatus.OK);
    }

    //retrieve all MOVIES
    @GetMapping("/movies")
    public ResponseEntity getMovies(){
        CustomizedResponse customizedResponse = new CustomizedResponse("A list of all movies", service.getMovies());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //retrieve all TV SHOWS
    @GetMapping("/tv")
    public ResponseEntity getTV(){
        CustomizedResponse customizedResponse = new CustomizedResponse("A list of all tv shows", service.getTV());
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //retrieve movie/tv show that has a certain title
    @GetMapping("/videos")
    public ResponseEntity getByTitle(@RequestParam(value="title") String title){
        CustomizedResponse customizedResponse = new CustomizedResponse("Movie or tv show with this certain title: "+ title, service.getByTitle(title));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //retrieve all featured movies
    @GetMapping("/movies/isFeatured")
    public ResponseEntity getFeaturedMovies(@RequestParam(value="isFeatured") String feature){
        CustomizedResponse customizedResponse = new CustomizedResponse("List of featured Movies", service.getFeaturedMovies(feature));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //retrieve all featured tv shows
    @GetMapping("/tv/isFeatured")
    public ResponseEntity getFeaturedTV(@RequestParam(value="isFeatured") String feature){
        CustomizedResponse customizedResponse = new CustomizedResponse("List of featured TV shows", service.getFeaturedTV(feature));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //retrieve movie/tv show by id
    @GetMapping("/videos/{id}")
    public ResponseEntity getMovieByID(@PathVariable("id") String id){

        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Getting a single video with id: "+id, Collections.singletonList(service.getAVideo(id)));
        } catch (Exception e) {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //updating movie / tv show
    @PutMapping(value="/movies/{id}", consumes={
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity updateMovie(@PathVariable("id") String id, @RequestBody Movie movie){
        CustomizedResponse customizedResponse = null;
        try{
            customizedResponse = new CustomizedResponse("Updated movie with id "+ id, Collections.singletonList(service.updateMovie(id, movie)));
        }catch(Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    //delete an existing movie or tv show.
    @DeleteMapping("/videos/{id}")
    public ResponseEntity deleteVideo(@PathVariable("id") String id) throws Exception {

        CustomizedResponse customizedResponse = null;
        try{
            customizedResponse = new CustomizedResponse("Deleted a movie with id "+ id, Collections.singletonList(service.deleteAVideo(id)));
        }catch(Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
}
