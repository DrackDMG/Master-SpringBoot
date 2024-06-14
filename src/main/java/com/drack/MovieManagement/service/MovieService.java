package com.drack.MovieManagement.service;

import com.drack.MovieManagement.percistence.entity.Movie;
import com.drack.MovieManagement.util.MovieGenre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<Movie> findAll();
    List<Movie> findAllByTitle(String title);
    List<Movie> findAllByGenre(MovieGenre genre);
    List<Movie> findAllByGenreAndTitle(MovieGenre genre, String title);

    Movie createOne(Movie movie);
    Movie updateOneById(Long id, Movie movie);
    void deleteOneById(Long id);

    Movie findOneById(Long id);
}
