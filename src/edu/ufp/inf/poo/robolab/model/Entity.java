package edu.ufp.inf.poo.robolab.model;

public abstract class Entity {
    private final String id;

    public Entity(String id) {
        // TODO: rejeitar id nulo ou vazio
        this.id = id;
       id.isBlank() { throw new IllegalArgumentException(); }
    }

    public String getId() { return id; }
}