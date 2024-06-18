package com.drack.MovieManagement.controller;

import ch.qos.logback.core.util.StringUtil;
import com.drack.MovieManagement.percistence.entity.Movie;
import com.drack.MovieManagement.service.MovieService;
import com.drack.MovieManagement.util.MovieGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(method = RequestMethod.GET)
    //@ResponseBody
    public List<Movie> findAll(@RequestParam(required = false, name = "titulo") String title,@RequestParam(required = false) MovieGenre genre) {
        List<Movie> movies = null;
        if(StringUtils.hasText(title) && genre != null){
            movies = movieService.findAllByGenreAndTitle(genre,title);
        }else if(StringUtils.hasText(title)){
            movies = movieService.findAllByTitle(title);
        }else if(genre != null){
            movies = movieService.findAllByGenre(genre);
        }else {
            movies = movieService.findAll();
        }
        return movies;
    }
    //para prueba de push


    /*
    @GetMapping(params = {"title","genre"})
    public List<Movie> findAllByGenreAndTitle(@RequestParam(required = false) String title,
                               @RequestParam(required = false) MovieGenre genre) {
        return movieService.findAllByGenreAndTitle(genre, title);
    }

    @GetMapping(params = {"title"})
    public List<Movie> findAllByTitle(@RequestParam(required = false) String title) {
        return movieService.findAllByTitle(title);
    }

    @GetMapping(params = {"genre"})
    public List<Movie> findAllByGenre(@RequestParam(required = false) MovieGenre genre) {
        return movieService.findAllByGenre(genre);
    }

    @GetMapping
    public List<Movie> findAll() {
        System.out.println("findAll");
        return movieService.findAll();
    }*/



        @GetMapping("/{id}")
    public Movie findById(@PathVariable Long id) {
        return movieService.findOneById(id);
    }


}
