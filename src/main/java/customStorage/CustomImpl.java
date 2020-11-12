package customStorage;

import model.Entity;
import storage.ImportAndExportStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CustomImpl extends ImportAndExportStorage {


    @Override
    public void open(File folder) {
        try {
            this.setEntities(new ArrayList<Entity>());
            for (File file : folder.listFiles()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {

                    String parser = scanner.nextLine();
                    Entity entity = new Entity();
                    if(parser.contains("#-"))
                        continue;
                    if(parser.startsWith("id/")){
                        String[] zvezdica =parser.split("/");
                        entity.setId(Integer.parseInt(zvezdica[1]));
                        continue;
                    }
                    if (parser.startsWith("name/")){
                        String[] zvezdica =parser.split("/");
                        entity.setId(Integer.parseInt(zvezdica[1]));
                        continue;
                    }



                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Entity> open(String s) {
        return null;
    }

    @Override
    public void save(File file) {

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
                    stringBuilder.append("  ");
                    stringBuilder.append(map.getKey());
                    stringBuilder.append("/");
                    stringBuilder.append(map.getValue());
                    stringBuilder.append("\n");
                    if (map.getValue() instanceof Entity) {
//                        for (Map.Entry<String,Entity> mapa2: entity.getAttributes().entrySet()){
//
//
//                        }
                        stringBuilder.append("[");
                        stringBuilder.append("\n");
                        stringBuilder.append("]");
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
