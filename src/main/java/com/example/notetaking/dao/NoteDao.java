package com.example.notetaking.dao;

import com.example.notetaking.model.Note;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteDao {

    int insertNote(UUID id, Note note);

    default int insertNote(Note note) {
        UUID id = UUID.randomUUID();
        return insertNote(id, note);
    }

    List<Note> getAll();

    Optional<Note> getById(UUID id);

    int updateById(UUID id, Note note);

    int deleteById(UUID id);
}
