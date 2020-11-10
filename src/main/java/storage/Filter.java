package storage;

import model.Entity;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    private static Filter instance = null;
    private static List<Entity> storage;


    private Filter() {
        storage = StorageManager.getBase().getEntities();
    }

    public static List<Entity> filter(int id, String name) {
        storage = StorageManager.getBase().getEntities();
        List<Entity> list = new ArrayList<Entity>();
        for (Entity entity : storage) {
            if (entity.getId() == id && entity.getName().equals(name))
                list.add(entity);
        }
        return list;
    }

    public static Filter getInstance() {
        if (instance == null)
            instance = new Filter();
        return instance;
    }

    public static List<Entity> getStorage() {
        return storage;
    }

}
