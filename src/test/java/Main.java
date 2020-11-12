import customStorage.CustomImpl;
import model.Entity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        CustomImpl impl = new CustomImpl();

        String path = "C:\\Users\\Nikola\\Desktop\\SK\\CustomImpl\\data\\data.txt";
        File file = new File("C:\\Users\\Nikola\\Desktop\\SK\\TestCustom");
        File file2 = new File(path);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();


        map3.put("ime", "Nikola");
        map3.put("prezime", "Paunovic");
        map3.put("predmet", "Sk");

        Entity ent = new Entity(1, "student", map3);
        map.put("prezime", "Paunovic");
        map.put("ime", "Paun");

        Entity ent2 = new Entity(2, "student", map);
        Entity ent3 = new Entity(3, "student", map);

        List<Entity> entities = new ArrayList<>();

        entities.add(ent);
        entities.add(ent2);
        entities.add(ent3);

        impl.save(entities, path);
        //impl.open(file2);


    }


}
