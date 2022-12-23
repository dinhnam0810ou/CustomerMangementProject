package com.ndn.service.impl;

import com.ndn.datasource.MovieDAO;
import com.ndn.model.Movie;
import com.ndn.model.PaginatedResult;
import com.ndn.service.IMovieService;

public class MovieService implements IMovieService {
    MovieDAO movieDAO = new MovieDAO();
    @Override
    public PaginatedResult<Movie> getMovies() {
        return movieDAO.getMovies();
    }
    @Override
    public Movie getMovieById(int movieId) {
        return movieDAO.getMovieById(movieId);
    }

}
