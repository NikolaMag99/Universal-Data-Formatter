package model;

import java.util.HashMap;
import java.util.Map;

public class Entity {

    private String name;
    private int id;
    private Map<String, Object> attributes;
    private Map<String, Entity> deca;

    public Entity(int id, String name) {
        super();
        this.id = id;
        this.name = name;
        this.attributes = new HashMap<String, Object>();
    }

    public Entity(int id, String name, Map<String, Object> attributes) {
        super();
        this.id = id;
        this.name = name;
        this.attributes = attributes;
    }

    public Entity() {
        this.attributes = new HashMap<String, Object>();
    }


    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", attributes=" + attributes +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Entity> getDeca() {
        return deca;
    }

    public void setDeca(Map<String, Entity> deca) {
        this.deca = deca;
    }
}
