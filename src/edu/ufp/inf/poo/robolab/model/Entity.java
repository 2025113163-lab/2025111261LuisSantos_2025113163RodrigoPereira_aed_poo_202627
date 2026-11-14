package edu.ufp.inf.poo.robolab.model;

public abstract class Entity {
    private final String id;

    public Entity(String id) throws IllegalAccessException {
        //rejeitar id nulo ou vazio
        if (id == null || id.isBlank()) {
           throw new IllegalAccessException();
        }

        this.id = id;
    }

    public String getId() { return id; }
}