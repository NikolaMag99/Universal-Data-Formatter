import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import model.Entity;
import storage.ImportAndExportStorage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YAMLImpl extends ImportAndExportStorage {

    static {
        //   StorageManager.registerStorage(new YAMLImpl());
    }

    private List<Entity> entiteti;

    public YAMLImpl() {
        entiteti = new ArrayList<Entity>();
    }

    @Override
    public List<Entity> open(String path) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        List<Entity> entiteti = null;
        try {
            entiteti = objectMapper.readValue(new File(path), new TypeReference<List<Entity>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entiteti;
    }

    @Override
    public void save(Entity entity) {
        entiteti.add(entity);
        try {
            writeEntityListToFile(entiteti, "C:\\Users\\Nikola\\Desktop\\SK\\YAMLimpl\\data\\data.yml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(List<Entity> entities, String path) {

        entiteti.addAll(entities);

        try {
            writeEntityListToFile(entiteti, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean writeEntityListToFile(List<Entity> entityList, String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        String yaml = mapper.writeValueAsString(entityList);
        stringToFile(path, yaml);
        return true;
    }


    private void stringToFile(String destinationPath, String data) {
        File file = new File(destinationPath);
        file.setWritable(true);
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] bytes = data.getBytes();
            bos.write(bytes);
            bos.close();
            fos.close();
            System.out.print("Data written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Entity> readFileToEntityList(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        List<Entity> toReturn = objectMapper.readValue(new File(path), new TypeReference<List<Entity>>() {
        });
        return toReturn;
    }


    @Override
    public void save(int i, String s, Map<String, Object> map) {

    }
}
