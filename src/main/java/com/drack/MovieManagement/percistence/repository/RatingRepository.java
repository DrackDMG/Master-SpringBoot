package com.drack.MovieManagement.percistence.repository;

import com.drack.MovieManagement.percistence.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findByMovieId(Long id);
    List<Rating> findByUserUsername(String username);

    @Query("SELECT r FROM Rating r JOIN r.user u WHERE  u.username = ?1")
    List<Rating> findByUsername(String username);
}
