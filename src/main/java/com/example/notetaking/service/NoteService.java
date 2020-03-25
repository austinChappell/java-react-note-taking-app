package com.example.notetaking.service;

import com.example.notetaking.dao.NoteDao;
import com.example.notetaking.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteService {

    private final NoteDao noteDao;

    @Autowired
    public NoteService(@Qualifier("postgres") NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public int insertOne(Note note) {
        UUID id = UUID.randomUUID();
        return noteDao.insertNote(id, note);
    }

    public List<Note> getAll() {
        return noteDao.getAll();
    }

    public Optional<Note> getById(UUID id) {
        return noteDao.getById(id);
    }

    public int updateById(UUID id, Note note) {
        return noteDao.updateById(id, note);
    }

    public int deleteById(UUID id) {
        return noteDao.deleteById(id);
    }
}
