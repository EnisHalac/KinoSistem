
// src/main/java/com/example/kinosistem/repository/DirectorRepository.java
package com.example.kinosistem.data;

import com.example.kinosistem.model.Director;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class DirectorRepository {

    private final JdbcTemplate jdbc;

    public DirectorRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static class DirectorRowMapper implements RowMapper<Director> {
        @Override
        public Director mapRow(ResultSet rs, int rowNum) throws SQLException {
            Director d = new Director();
            // Adjust setter names if your compiled class differs:
            try { d.getClass().getMethod("setId", int.class).invoke(d, rs.getInt("id")); } catch (Exception ignored) {}
            try { d.getClass().getMethod("setFullName", String.class).invoke(d, rs.getString("full_name")); } catch (Exception ignored) {}
            try { d.getClass().getMethod("setCountry", String.class).invoke(d, rs.getString("country")); } catch (Exception ignored) {}
            try { d.getClass().getMethod("setBirthDate", java.sql.Date.class).invoke(d, rs.getDate("birth_date")); } catch (Exception ignored) {}
            return d;
        }
    }

    public List<Director> findAll() {
        return jdbc.query("SELECT id, full_name, country, birth_date FROM directors ORDER BY created_at DESC",
                new DirectorRowMapper());
    }

    public Optional<Director> findById(int id) {
        List<Director> list = jdbc.query("SELECT id, full_name, country, birth_date FROM directors WHERE id=?",
                new DirectorRowMapper(), id);
        return list.stream().findFirst();
    }

    public int create(String fullName, String country, java.sql.Date birthDate) {
        return jdbc.update("INSERT INTO directors(full_name, country, birth_date) VALUES (?,?,?)",
                fullName, country, birthDate);
    }

    public int delete(int id) {
        return jdbc.update("DELETE FROM directors WHERE id=?", id);
    }
}
