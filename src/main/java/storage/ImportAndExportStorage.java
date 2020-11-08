package storage;

import model.Entity;

import java.io.File;
import java.util.List;
import java.util.Map;

public abstract class ImportAndExportStorage {
    private List<Entity> entities;
    private String baseName;

//    public abstract void open(File file);

    public abstract List<Entity> open(String path);

//    public abstract void save(File folder);

    public abstract void save(List<Entity> entities, String path);

    public abstract void save(Entity entity);

    public abstract void save(int id, String name, Map<String, Object> attributes);

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
