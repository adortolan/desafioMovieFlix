package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {

        Long movieId = dto.getMovieId();
        Movie movie = movieRepository.getReferenceById(movieId);
        User user = authService.authenticated();

        Review entity = new Review();

        entity.setText(dto.getText());
        entity.setMovie(movie);
        entity = repository.save(entity);
        entity.setUser(user);
        entity = repository.save(entity);
        return new ReviewDTO(entity);
    }
}
