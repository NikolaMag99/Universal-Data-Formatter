package JsonStorage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gson.stream.JsonReader;

import model.Entity;
import storage.ImportAndExportStorage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONImpl extends ImportAndExportStorage {

    private List<Entity> entities;


    private JsonReader jsonReader;
    static {

    }

    public JSONImpl(List<Entity> entities) {
        this.entities = entities;
    }

    public JSONImpl() {

    }

    @Override
    public List<Entity> open(String s) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Entity> entiteti = null;
        try {
            entiteti = objectMapper.readValue(new File(s), new TypeReference<List<Entity>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entiteti;
    }

    @Override
    public void save(List<Entity> list, String s) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(s), list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Entity entity) {

    }

    @Override
    public void save(int i, String s, Map<String, Object> map) {

    }

}
