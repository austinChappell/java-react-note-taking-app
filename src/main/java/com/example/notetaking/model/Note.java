package com.example.notetaking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Note {

    private UUID id;
    private String title;
    private String body;

    public Note(
            @JsonProperty("id") UUID id,
            @JsonProperty("title") String title,
            @JsonProperty("body") String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
