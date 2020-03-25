package com.example.notetaking.api;

import com.example.notetaking.model.Note;
import com.example.notetaking.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/notes")
@RestController
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public int insertOne(@RequestBody Note note) {
        return noteService.insertOne(note);
    }

    @GetMapping
    public List<Note> getAll() {
        return noteService.getAll();
    }

    @GetMapping(path = "{id}")
    public Optional<Note> getById(@PathVariable("id") UUID id) {
        Optional<Note> note = noteService.getById(id);

        if (note == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Note not found"
            );
        }

        return note;
    }

    @PutMapping(path = "{id}")
    public int updateById(
            @PathVariable("id") UUID id,
            @RequestBody Note note) {
        return noteService.updateById(id, note);
    }

    @DeleteMapping(path = "{id}")
    public int deleteById(@PathVariable("id") UUID id) {
        return noteService.deleteById(id);
    }
}
