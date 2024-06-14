package com.drack.MovieManagement.service.impl;

import com.drack.MovieManagement.exception.ObjectNotFoundException;
import com.drack.MovieManagement.percistence.entity.Rating;
import com.drack.MovieManagement.percistence.repository.RatingRepository;
import com.drack.MovieManagement.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> findAllByMovieId(Long id) {
        return ratingRepository.findByMovieId(id);
    }

    @Override
    public List<Rating> findAllByUsername(String username) {
        return ratingRepository.findByUsername(username);
    }

    @Override
    public Rating findOneById(Long id) {
        return ratingRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("[rating:" + Long.toString(id) +"]"));
    }

    @Override
    public Rating createOne(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateOneById(Long id, Rating rating) {
        Rating oldRating = this.findOneById(id);
        oldRating.setUserId(rating.getUserId());
        oldRating.setMovieId(rating.getMovieId());
        return ratingRepository.save(oldRating);
    }

    @Override
    public void deleteOneById(Long id) {
        if (ratingRepository.existsById(id)) {
            ratingRepository.deleteById(id);
            return;
        }
        throw new ObjectNotFoundException("[rating:" + Long.toString(id) +"]");
    }
}
