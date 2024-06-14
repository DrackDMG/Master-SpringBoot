package com.drack.MovieManagement.percistence.repository;

import com.drack.MovieManagement.percistence.entity.Movie;
import com.drack.MovieManagement.util.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleContaining(String title);
    List<Movie> findByGenre(MovieGenre genre);
    List<Movie> findByGenreAndTitleContaining(MovieGenre genre, String title);


}
