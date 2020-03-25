package com.example.notetaking.dao;

import com.example.notetaking.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class NoteDataAccessService implements NoteDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NoteDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertNote(UUID id, Note note) {
        final String sql = "INSERT INTO notes (id, title, body) VALUES (?, ?, ?)";
        return this.jdbcTemplate.update(sql, id, note.getTitle(), note.getBody());
    }

    @Override
    public List<Note> getAll() {
        final String sql = "SELECT id, title, body FROM notes";

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String title = resultSet.getString("title");
            String body = resultSet.getString("body");

            return new Note(id, title, body);
        });
    }

    @Override
    public Optional<Note> getById(UUID id) {
        final String sql = "SELECT id, title, body FROM notes WHERE id = ?";

        try {
            Note note = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    new RowMapper<Note>() {
                        @Override
                        public Note mapRow(ResultSet resultSet, int i) throws SQLException {
                            UUID id = UUID.fromString(resultSet.getString("id"));
                            String title = resultSet.getString("title");
                            String body = resultSet.getString("body");

                            return new Note(id, title, body);
                        }
                    }
            );

            return Optional.ofNullable(note);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int updateById(UUID id, Note note) {
        final String sql = "UPDATE notes SET title = ?, body = ? WHERE id = ?";

        return jdbcTemplate.update(sql, note.getTitle(), note.getBody(), id);
    }

    @Override
    public int deleteById(UUID id) {
        final String sql = "DELETE FROM notes WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }
}
