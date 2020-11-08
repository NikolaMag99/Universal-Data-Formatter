import JsonStorage.JSONImpl;
import model.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        String putanja = "C:\\Users\\Nikola\\Desktop\\SK\\JSONImpl\\date\\proba.json";
        JSONImpl json = new JSONImpl();
        List<Entity> entities = new ArrayList<>();
        List<Entity> noviEnti = new ArrayList<>();
        HashMap<String, Object> mapa= new HashMap<>();

        mapa.put("ime", "Prezime");
        mapa.put("godine", 1212);
        Entity maksa = new Entity(1, "maksa", mapa);
        Entity paun = new Entity(2,"paun", mapa);

        entities.add(maksa);
        entities.add(paun);

        // json.save(entities, putanja);
        noviEnti = json.open(putanja);
        for(Entity e: noviEnti){
            System.out.println(e);
        }
    }
}
