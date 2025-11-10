
// src/main/java/com/example/kinosistem/repository/MovieRepository.java
package com.example.kinosistem.data;

import com.example.kinosistem.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository {

    private final JdbcTemplate jdbc;

    public MovieRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static class MovieRowMapper implements RowMapper<Movie> {
        @Override
        public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Movie m = new Movie();
            try { m.getClass().getMethod("setId", int.class).invoke(m, rs.getInt("id")); } catch (Exception ignored) {}
            try { m.getClass().getMethod("setTitle", String.class).invoke(m, rs.getString("title")); } catch (Exception ignored) {}
            try { m.getClass().getMethod("setReleaseYear", Integer.class).invoke(m, (Integer)rs.getObject("release_year")); } catch (Exception ignored) {}
            try { m.getClass().getMethod("setGenre", String.class).invoke(m, rs.getString("genre")); } catch (Exception ignored) {}
            try { m.getClass().getMethod("setDurationMin", Integer.class).invoke(m, (Integer)rs.getObject("duration_min")); } catch (Exception ignored) {}
            try { m.getClass().getMethod("setDirectorId", Integer.class).invoke(m, (Integer)rs.getObject("director_id")); } catch (Exception ignored) {}
            try { m.getClass().getMethod("setRating", Double.class).invoke(m, (Double)rs.getObject("rating")); } catch (Exception ignored) {}
            return m;
        }
    }

    public List<Movie> findAll() {
        String sql = "SELECT id, title, release_year, genre, duration_min, director_id, rating FROM movies ORDER BY created_at DESC";
        return jdbc.query(sql, new MovieRowMapper());
    }

    public Optional<Movie> findById(int id) {
        String sql = "SELECT id, title, release_year, genre, duration_min, director_id, rating FROM movies WHERE id=?";
        List<Movie> list = jdbc.query(sql, new MovieRowMapper(), id);
        return list.stream().findFirst();
    }

    public int create(String title, Integer releaseYear, String genre, Integer durationMin, Integer directorId, Double rating) {
        String sql = "INSERT INTO movies(title, release_year, genre, duration_min, director_id, rating) VALUES (?,?,?,?,?,?)";
        return jdbc.update(sql, title, releaseYear, genre, durationMin, directorId, rating);
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM movies WHERE id=?", id);
    }
}
