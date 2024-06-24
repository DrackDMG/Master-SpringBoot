package com.drack.MovieManagement.controller;

import ch.qos.logback.core.util.StringUtil;
import com.drack.MovieManagement.exception.ObjectNotFoundException;
import com.drack.MovieManagement.percistence.entity.Movie;
import com.drack.MovieManagement.service.MovieService;
import com.drack.MovieManagement.util.MovieGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Movie>> findAll(@RequestParam(required = false, name = "titulo") String title, @RequestParam(required = false) MovieGenre genre) {
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

        //HttpHeaders headers = new HttpHeaders();
       // return new ResponseEntity(movies, headers, HttpStatusCode.valueOf(200)); //opcin 1
        //return ResponseEntity.status(200).body(movies); //opcion 2
        return ResponseEntity.ok(movies); //opcion 3
    }

        @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(movieService.findOneById(id));
        }catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
