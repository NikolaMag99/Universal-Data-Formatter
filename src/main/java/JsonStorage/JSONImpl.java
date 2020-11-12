package JsonStorage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import model.Entity;
import storage.ImportAndExportStorage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import storage.StorageManager;

public class JSONImpl extends ImportAndExportStorage {

    private List<Entity> entities;


    private JsonReader jsonReader;

    static {
        StorageManager.setImportAdnExport(new JSONImpl());
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
    public void save(File file) {

    }

    @Override
    public void save(List<Entity> list, String s) {
        ObjectMapper objectMapper = new ObjectMapper();
        File folder = new File(s);
        for (File file: folder.listFiles()) {
            try {
                objectMapper.writeValue(file, list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void save(File folder, String name) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(name), folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void open(File folder) {
        try {
            this.setEntities(new ArrayList<Entity>());
            for (File file : folder.listFiles()) {
                JsonParser jsonParser = new JsonParser();
                JsonArray jArray = jsonParser.parse(new FileReader(file)).getAsJsonArray();
                Gson gson = new Gson();
                for (JsonElement element : jArray) {
                    this.getEntities().add(gson.fromJson(element, Entity.class));
                }
            }
            for (Entity entity : this.getEntities())
                System.out.println(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
