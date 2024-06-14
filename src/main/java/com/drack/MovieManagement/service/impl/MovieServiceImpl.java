package com.drack.MovieManagement.service.impl;

import com.drack.MovieManagement.exception.ObjectNotFoundException;
import com.drack.MovieManagement.percistence.entity.Movie;
import com.drack.MovieManagement.percistence.repository.MovieRepository;
import com.drack.MovieManagement.service.MovieService;
import com.drack.MovieManagement.util.MovieGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> findAllByTitle(String title) {
        return movieRepository.findByTitleContaining(title);
    }

    @Override
    public List<Movie> findAllByGenre(MovieGenre genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public List<Movie> findAllByGenreAndTitle(MovieGenre genre, String title) {
        return movieRepository.findByGenreAndTitleContaining(genre, title);
    }

    @Override
    public Movie createOne(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateOneById(Long id, Movie movie) {
        Movie oldMovie =this.findOneById(id);
        oldMovie.setTitle(movie.getTitle());
        oldMovie.setGenre(movie.getGenre());
        oldMovie.setDirector(movie.getDirector());
        oldMovie.setYear(movie.getYear());
        return movieRepository.save(oldMovie);
    }

    @Override
    public void deleteOneById(Long id) {
        Movie movie = this.findOneById(id);
        movieRepository.delete(movie);
    }

    @Override
    public Movie findOneById(Long id) {
        Movie movie = movieRepository
                .findById(id)
                .orElseThrow(
                        ()-> new ObjectNotFoundException("[movie:" + Long.toString(id)  + "]")
                        );
        return movie;
    }
}
