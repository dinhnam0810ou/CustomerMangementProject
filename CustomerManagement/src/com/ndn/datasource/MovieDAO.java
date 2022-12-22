package com.ndn.datasource;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.ndn.model.Movie;
import com.ndn.model.PaginatedResult;

public class MovieDAO {
    public PaginatedResult<Movie> getMovies() {
        PaginatedResult<Movie> paginatedResult = new PaginatedResult<>();
        PreparedStatement preparedStatement = null;
        List<Movie> movies = new ArrayList<>();
        String sql = "select * from movie";
        try {
            preparedStatement = DatabaseConnection.getConnection().prepareStatement(sql);    
            ResultSet rs = preparedStatement.executeQuery();
            while ( rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                Movie movie = new Movie();
                movie.setId(id);
                movie.setName(name);
                movie.setPrice(price);
                movies.add(movie);
            }
            paginatedResult.setItems(movies);
            paginatedResult.setCount(movies.size());
            return paginatedResult;
        } catch (SQLException e) {
            return null;
        } finally {
            closeQuietly(preparedStatement);
        }
    }

    private void closeQuietly(Statement statement) {
        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public Movie getMovieById(int movieId) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DatabaseConnection.getConnection().prepareStatement("select * from movie where id = ?");
            preparedStatement.setInt(1, movieId);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            
            int id = rs.getInt("id");
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            Movie movie = new Movie();
            movie.setId(id);
            movie.setName(name);
            movie.setPrice(price);
            return movie;
            
        } catch (SQLException e) {
            return null;
        } finally {
            closeQuietly(preparedStatement);
        }
    }
}
