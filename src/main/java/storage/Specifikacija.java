package storage;

import model.Entity;
import sort_filter.Filter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Specifikacija {

    private String path;
    private List<Entity> entities;
    private ImportAndExportStorage storage;

    public Specifikacija(ImportAndExportStorage storage) {
        this.storage = storage;
    }

    //Crud Operacije

    public void createEntity(int id, String name, Map<String, Object> attributes) {
        Entity e = new Entity(id, name, attributes);
        storage.save(e);
    }

    public void createEntity(String name, Map<String, Object> attributes) {
        int id = 1;
        for (Entity entity : storage.getEntities()) {
            if (entity.getId() == id) {
                id++;
            }
        }
        createEntity(id, name, attributes);
    }

    public void createEntity(int id, String name) {
        createEntity(id, name, new HashMap<String, Object>());
    }

    public void createEntity(String name) {
        createEntity(name, new HashMap<String, Object>());
    }

    public void deleteEntity(int id, String name) {
        Entity obj = null;
        for (Entity e : storage.getEntities()) {
            if (e.getId() == id && e.getName() == name)
                obj = e;
        }
        storage.getEntities().remove(obj);
        storage.save((Entity) storage.getEntities());
    }

    public void updateEntity(int id, String name, String key, String value) {
        List<Entity> ls = Filter.filter(id, name);
        for (Entity e : ls) {
            e.getAttributes().put(key, value);
        }
        storage.save((Entity) storage.getEntities());
    }

    public void updateEntity(int id, String name, Map<String, Object> attributes) {
        List<Entity> ls = Filter.filter(id, name);
        for (Entity e : ls) {
            e.setAttributes(attributes);
        }
        storage.save((Entity) storage.getEntities());
    }

    public void updateEntity(int oldId, int newId, String name, Map<String, Object> attributes) {
        List<Entity> ls = Filter.filter(oldId, name);
        for (Entity e : ls) {
            e.setId(newId);
        }
        storage.save((Entity) storage.getEntities());
    }

    public void updateEntity(int id, String newName, String oldName, Map<String, Object> attributes) {
        List<Entity> ls = Filter.filter(id, oldName);
        for (Entity e : ls) {
            e.setName(newName);
        }
        storage.save((Entity) storage.getEntities());
    }

//    // brisemo sve entitete pod nazivom studIndex za studProgram recimo
//    public void remove(String name, Object keyField, Object valueField) {
//
//    }
//
//    public void Entity search(int id);
//
//    public void List<Entity> search(String name);
//
//    public void List<Entity> search(Map<Object, Object> values);
//
//    public void List<Entity> search(String name, Map<Object, Object> values);
//
//    public void List<Entity> search(String name, Object key, Object value, Object innerKey);
//
//    public void List<Entity> sort(boolean byName, boolean byId, boolean desc);
//
//    public void List<Entity> sortSearch(String name, Map<Object, Boolean> descOrAsc);
//
//    public void List<Entity> sortSearch(String name, Map<Object, Object> values, Map<Object, Boolean> descOrAsc);
//
//    public void List<Entity> sortSearch(String name, Object key, Object value, Object innerKey, Map<Object, Boolean> descOrAsc);


}
