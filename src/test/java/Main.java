import customStorage.CustomImpl;
import model.Entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        CustomImpl impl = new CustomImpl();

        String path = "C:\\Users\\Nikola\\Desktop\\SK\\CustomImpl\\data\\data.txt";

        Map<String, Object> map = new HashMap<>();
        Map<String, Entity> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();



        map.put("ime", "Paun");
        map.put("prezime", "Paun");
        map.put("ime", "Paun");


        Entity ent = new Entity(1, "student", map);

       // map.put("ime", ent);
        Entity ent2 = new Entity(2, "student", map);
        Entity ent3 = new Entity(3, "student", map);

        List<Entity> entities = new ArrayList<>();

        entities.add(ent);
        entities.add(ent2);
        entities.add(ent3);

        impl.save(entities, path);
    }


}
