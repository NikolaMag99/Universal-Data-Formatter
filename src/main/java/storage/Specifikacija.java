package storage;

import model.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Specifikacija {

    private String path;
    private List<Entity> entities;
    private static List<Entity> baza;
    private ImportAndExportStorage storage;

    public Specifikacija(ImportAndExportStorage storage) {
        this.storage = storage;
    }

    //Crud Operacije

    public void createEntity(int id, String name, Map<String, Object> attributes) {
        Entity e = new Entity(id, name, attributes);
        storage.getEntities().add(e);
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
        storage.save(storage.getEntities(), storage.getBaseName());
    }

    public void updateEntity(int id, String name, String key, String value) {
        List<Entity> list = Filter.filter(id, name);
        for (Entity e : list) {
            e.getAttributes().put(key, value);
        }
        storage.save(storage.getEntities(), storage.getBaseName());
    }

    public void updateEntity(int id, String name, Map<String, Object> attributes) {
        List<Entity> list = Filter.filter(id, name);
        for (Entity e : list) {
            e.setAttributes(attributes);
        }
        storage.save(storage.getEntities(),  storage.getBaseName());
    }

    public void updateEntity(int oldId, int newId, String name, Map<String, Object> attributes) {
        List<Entity> list = Filter.filter(oldId, name);
        for (Entity e : list) {
            e.setId(newId);
        }
        storage.save(storage.getEntities(), storage.getBaseName());
    }

    public void updateEntity(int id, String newName, String oldName, Map<String, Object> attributes) {
        List<Entity> list = Filter.filter(id, oldName);
        for (Entity e : list) {
            e.setName(newName);
        }
        storage.save(storage.getEntities(), storage.getBaseName());
    }

//    public static List<Entity> filter(int id, String name) {
//        baza = StorageManager.getBase().getEntities();
//        List<Entity> list = new ArrayList<Entity>();
//        for (Entity entity : baza) {
//            if (entity.getId() == id && entity.getName().equals(name))
//                list.add(entity);
//        }
//        return list;
//    }
}