package storage;

import model.Entity;

import java.io.File;
import java.util.List;
import java.util.Map;

public abstract class ImportAndExportStorage {
    private List<Entity> entities;
    private String baseName;

    public abstract void open(File file);

    public abstract List<Entity> open(String path);


    public abstract void save(File folder);

    public abstract void save(List<Entity> entities, String path);

//    public void save(List<Entity> entities) {
//        File f = new File(baseName);
//        save(f);
//    }

    public void save(Entity entity) {
        entities.add(entity);
        save(entities, baseName);
    }

    public void save(int id, String name, Map<String, Object> attributes) {
        Entity entity = new Entity(id, name, attributes);
        entities.add(entity);
        save(entities, baseName);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }


}
