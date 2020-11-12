package YAMLStorage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import model.Entity;
import storage.ImportAndExportStorage;
import storage.StorageManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YAMLImpl extends ImportAndExportStorage {

    static {
        StorageManager.setImportAdnExport(new YAMLImpl());
    }

    private List<Entity> entiteti;

    public YAMLImpl() {
        entiteti = new ArrayList<Entity>();
    }

    @Override
    public void open(File folder) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        this.setEntities(new ArrayList<>());
        try {
            this.setEntities(new ArrayList<Entity>());
            for (File file : folder.listFiles()) {
                this.getEntities().addAll(mapper.readValue(file, new TypeReference<List<Entity>>() {
                }));
            }
            for (Entity entity : this.getEntities())
                System.out.println(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


//    @Override
//    public List<Entity> open(String path) {
//        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
//        List<Entity> entiteti = null;
//        try {
//            entiteti = objectMapper.readValue(new File(path), new TypeReference<List<Entity>>() {
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return entiteti;
//    }
//
//
//    @Override
//    public void save(File file) {
//
//    }

//    @Override
//    public void save(Entity entity) {
//        File file = new File("C:\\Users\\Nikola\\Desktop\\SK\\YAMLimpl\\data\\data.yml");
//        entiteti.add(entity);
//        try {
//            writeEntityListToFile(entiteti, file);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Metoda za cuvanje entitea u file
     *
     * @param entities List entiteta koja treba da se cuva
     * @param path     Putanja do fajla u koji treba da se sacuva
     */
    @Override
    public void save(List<Entity> entities, String path) {

        File folder = new File(path);
        for (File file : folder.listFiles()) {
            try {
                writeEntityListToFile(entities, file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void writeEntityListToFile(List<Entity> entityList, File file) throws Exception {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        mapper.writeValue(file, entityList);
    }


    private void stringToFile(File file, String data) {
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


//    @Override
//    public void save(int i, String s, Map<String, Object> map) {
//
//    }
}
