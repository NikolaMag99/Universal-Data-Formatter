package storage;

import model.Entity;

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

    /**
     * Metoda za kreiranje entiteta sa novim id-om, imenom i atributima
     *
     * @param id         jedinstven id novog entiteta
     * @param name       ime entiteta
     * @param attributes svi atributi koje taj entite ima
     */
    public void createEntity(int id, String name, Map<String, Object> attributes) {
        Entity e = new Entity(id, name, attributes);
        storage.getEntities().add(e);
        storage.save(storage.getEntities(), storage.getBaseName());
    }

    /**
     * Metoda za kreiranje entiteta sa imenom i atributima
     *
     * @param name       ime entiteta
     * @param attributes svi atributi koje taj entite ima
     */
    public void createEntity(String name, Map<String, Object> attributes) {
        int id = 1;
        for (Entity entity : storage.getEntities()) {
            if (entity.getId() == id) {
                id++;
            }
        }
        createEntity(id, name, attributes);
    }

    /**
     * Metoda za kreiranje entiteta sa idom i imenom
     *
     * @param id   jedinstven id novog entiteta
     * @param name ime entiteta
     */
    public void createEntity(int id, String name) {
        createEntity(id, name, new HashMap<String, Object>());
    }

    /**
     * Metoda za kreiranje entiteta samo sa imenom
     *
     * @param name ime entiteta
     */
    public void createEntity(String name) {
        createEntity(name, new HashMap<String, Object>());
    }

    /**
     * Metoda za brisanje entiteta po njegovom id-u i imenu
     *
     * @param id   entiteta kog brisemo
     * @param name ime entiteta kog brisemo
     */
    public void deleteEntity(int id, String name) {
        Entity obj = null;
        for (Entity e : storage.getEntities()) {
            if (e.getId() == id && e.getName() == name)
                obj = e;
        }
        storage.getEntities().remove(obj);
        storage.save(storage.getEntities(), storage.getBaseName());
    }

    /**
     * Metoda za update selektovanog entiteta po svim parametrima
     *
     * @param id    entiteta
     * @param name  ime entiteta
     * @param key   kljuc entita ili ti atribut
     * @param value vrednost tog atributa
     */
    public void updateEntity(int id, String name, String key, String value) {
        List<Entity> list = Filter.filter(id, name);
        for (Entity e : list) {
            e.getAttributes().put(key, value);
        }
        storage.save(storage.getEntities(), storage.getBaseName());
    }

    /**
     * Metoda za update selektovanog entiteta po svim parametrima
     *
     * @param id         entiteta
     * @param name       ime entiteta
     * @param attributes svi atributi tog entiteta
     */
    public void updateEntity(int id, String name, Map<String, Object> attributes) {
        List<Entity> list = Filter.filter(id, name);
        for (Entity e : list) {
            e.setAttributes(attributes);
        }
        storage.save(storage.getEntities(), storage.getBaseName());
    }

    /**
     * Metoda za update id-a selektovanog entiteta
     *
     * @param oldId      stari id entiteta
     * @param newId      novi id entiteta
     * @param name       ime entiteta
     * @param attributes svi atributi entiteta
     */
    public void updateEntity(int oldId, int newId, String name, Map<String, Object> attributes) {
        List<Entity> list = Filter.filter(oldId, name);
        for (Entity e : list) {
            e.setId(newId);
        }
        storage.save(storage.getEntities(), storage.getBaseName());
    }

    /**
     * Metoda za update imena selektovanog entiteta
     *
     * @param id         entiteta
     * @param newName    novo ime entiteta
     * @param oldName    staro ime entiteta
     * @param attributes svi atributi entiteta
     */
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
