package com.ndn.service;

import com.ndn.model.Movie;
import com.ndn.model.PaginatedResult;

public interface IMovieService {
    public PaginatedResult<Movie> getMovies();
    public Movie getMovieById(int movieId);
}
