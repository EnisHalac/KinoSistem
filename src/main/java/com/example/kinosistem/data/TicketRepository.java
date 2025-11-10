
// src/main/java/com/example/kinosistem/repository/TicketRepository.java
package com.example.kinosistem.data;

import com.example.kinosistem.model.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TicketRepository {

    private final JdbcTemplate jdbc;

    public TicketRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private static class TicketRowMapper implements RowMapper<Ticket> {
        @Override
        public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ticket t = new Ticket();
            try { t.getClass().getMethod("setId", long.class).invoke(t, rs.getLong("id")); } catch (Exception ignored) {}
            try { t.getClass().getMethod("setMovieId", Integer.class).invoke(t, (Integer)rs.getObject("movie_id")); } catch (Exception ignored) {}
            try { t.getClass().getMethod("setSeatLabel", String.class).invoke(t, rs.getString("seat_label")); } catch (Exception ignored) {}
            try { t.getClass().getMethod("setPrice", java.math.BigDecimal.class).invoke(t, rs.getBigDecimal("price")); } catch (Exception ignored) {}
            try { t.getClass().getMethod("setCurrency", String.class).invoke(t, rs.getString("currency")); } catch (Exception ignored) {}
            try { t.getClass().getMethod("setPurchasedAt", java.sql.Timestamp.class).invoke(t, rs.getTimestamp("purchased_at")); } catch (Exception ignored) {}
            try { t.getClass().getMethod("setBuyerName", String.class).invoke(t, rs.getString("buyer_name")); } catch (Exception ignored) {}
            return t;
        }
    }

    public List<Ticket> listForMovie(int movieId) {
        String sql = "SELECT id, movie_id, seat_label, price, currency, purchased_at, buyer_name FROM tickets WHERE movie_id=? ORDER BY purchased_at DESC";
        return jdbc.query(sql, new TicketRowMapper(), movieId);
    }

    public int create(int movieId, String seatLabel, java.math.BigDecimal price, String currency, java.sql.Timestamp purchasedAt, String buyerName) {
        String sql = "INSERT INTO tickets(movie_id, seat_label, price, currency, purchased_at, buyer_name) VALUES (?,?,?,?,?,?)";
        return jdbc.update(sql, movieId, seatLabel, price, currency, purchasedAt, buyerName);
    }

    public int delete(long id) {
        return jdbc.update("DELETE FROM tickets WHERE id=?", id);
    }
}
