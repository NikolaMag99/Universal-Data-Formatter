package customStorage;

import model.Entity;
import storage.ImportAndExportStorage;
import storage.StorageManager;

import java.io.*;
import java.util.*;

public class CustomImpl extends ImportAndExportStorage {

    static {
        StorageManager.setImportAdnExport(new CustomImpl());
    }

    @Override
    public void open(File folder) {

        ArrayList<Entity> entitetit = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(folder);

            while (scanner.hasNext()) {
                String parser = scanner.nextLine();
                Entity entity = new Entity();
                if (parser.contains("#-"))
                    continue;

                if (parser.startsWith("id/")) {
                    String razmak[] = parser.split("/");
                    entity.setId(Integer.parseInt(razmak[1]));
                    entitetit.add(entity);
                    continue;
                }

                if (parser.startsWith("name/")) {
                    String razmak[] = parser.split("/");
                    entity.setName((razmak[1]));
                    continue;
                }
                Map<String, Object> attributes = new HashMap<String, Object>();
                Map<String, Object> entAtr = new HashMap<String, Object>();

                if (parser.startsWith("attributes..")) {
                    scanner.nextLine();
                    while (scanner.nextLine().equals("-#")) {
                        parser.startsWith("\t");
                        String razmak[] = parser.split("/");
                        attributes.put(razmak[0], razmak[1]);
                        entity.setAttributes(attributes);

                        if (scanner.nextLine().equals("["))
                            continue;
                        if (parser.startsWith("id/")) {
                            String razmak1[] = parser.split("/");
                            entity.setId(Integer.parseInt(razmak1[1]));
                            continue;
                        }
                        if (parser.startsWith("name/")) {
                            String razmak2[] = parser.split("/");
                            entity.setName(razmak2[1]);

                            continue;
                        }
                        if (parser.startsWith("attributes..")) {
                            scanner.nextLine();
                            while (scanner.nextLine().equals("]")) {
                                String razmakAtr[] = parser.split("/");
                                entAtr.put(razmakAtr[0], razmakAtr[1]);
                                entity.setAttributes(entAtr);

                            }
                        }

                    }

                }

                if (parser.endsWith("-#")) {
                    continue;
                }

            }

            for (Entity e : entitetit)
                System.out.println(e);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }


    }


    @Override
    public void save(List<Entity> list, String s) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            File file = new File(s);

            file.setWritable(true);

            for (Entity entity : list) {
                stringBuilder.append("#-");
                stringBuilder.append("\n");
                stringBuilder.append("id/");
                stringBuilder.append(entity.getId());
                stringBuilder.append("\n");
                stringBuilder.append("name/");
                stringBuilder.append(entity.getName());
                stringBuilder.append("\n");
                stringBuilder.append("attributes..");
                stringBuilder.append("\n");
                for (Map.Entry<String, Object> map : entity.getAttributes().entrySet()) {
                    stringBuilder.append("\t");
                    stringBuilder.append(map.getKey());
                    stringBuilder.append("/");
                    stringBuilder.append(map.getValue());
                    stringBuilder.append("\n");
                    if (map.getValue() instanceof Entity) {
                        for (Map.Entry<String, Entity> mapa2 : entity.getDeca().entrySet()) {
                            stringBuilder.append("[");
                            stringBuilder.append("\n");
                            stringBuilder.append("\t");
                            stringBuilder.append("name/");
                            stringBuilder.append(entity.getName());
                            if (stringBuilder.append("attributes..").equals(true)) {
                                stringBuilder.append("\n");
                                for (Map.Entry<String, Object> atributi : entity.getAttributes().entrySet()) {
                                    stringBuilder.append("\t");
                                    stringBuilder.append(map.getKey());
                                    stringBuilder.append("/");
                                    stringBuilder.append(map.getValue());
                                    stringBuilder.append("\n");
                                }
                            }
                            stringBuilder.append("]");
                        }
                    }
                }
                stringBuilder.append("-#");
                stringBuilder.append("\n");

            }

            try (FileOutputStream fos = new FileOutputStream(file);
                 BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                byte[] bytes = stringBuilder.toString().getBytes();
                bos.write(bytes);
                bos.close();
                fos.close();
                System.out.print("Data written to file successfully.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
