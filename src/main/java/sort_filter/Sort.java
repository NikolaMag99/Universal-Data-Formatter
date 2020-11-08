package sort_filter;

import enums.SortType;
import model.Entity;
import storage.ImportAndExportStorage;
import storage.StorageManager;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
    private static Sort instance = null;
    private static boolean inceresaing = true;
    private static List<Entity> list;
    private static String atribute;
    private static boolean ascending = false;


    public static boolean isInceresaing() {
        return inceresaing;
    }

    public static void setInceresaing(boolean inceresaing) {
        Sort.inceresaing = inceresaing;
    }

    public static List<Entity> getList() {
        return list;
    }

    public static void setList(List<Entity> list) {
        Sort.list = list;
    }

    public static boolean isAscending() {
        return ascending;
    }

    public static void setAscending(boolean ascending) {
        Sort.ascending = ascending;
    }

    public static void setInstance(Sort instance) {
        Sort.instance = instance;
    }

    private Sort() {

    }

    public static Sort getInstance() {
        if (instance == null)
            instance = new Sort();
        return instance;
    }

    private class SortEntities implements Comparator<Entity> {

        public int compare(Entity entity1, Entity entity2) {
            String str1 = (String) entity1.getAttributes().getOrDefault(atribute, "");
            String str2 = (String) entity2.getAttributes().getOrDefault(atribute, "");
            if (ascending)
                return str1.compareTo(str2);
            else
                return str2.compareTo(str1);
        }
    }


    public void sort(boolean asc, SortType type) {
        ImportAndExportStorage base = StorageManager.getBase();
        if (type == SortType.ID) {
            if (asc)
                Collections.sort(base.getEntities(), (entity1, entity2) -> entity1.getId() - entity2.getId());
            else
                Collections.sort(base.getEntities(), (entity1, entity2) -> entity2.getId() - entity1.getId());
        } else {
            if (asc)
                Collections.sort(base.getEntities(), (entity1, entity2) -> entity1.getName().compareTo(entity2.getName()));
            else
                Collections.sort(base.getEntities(), (entity1, entity2) -> entity2.getName().compareTo(entity2.getName()));
        }
    }

    public void sort(String atributeStr, boolean asc) {
        ImportAndExportStorage base = StorageManager.getBase();
        this.setAtribute(atributeStr);
        this.ascending = asc;
        Collections.sort(base.getEntities(), new SortEntities());
    }
    
    public static String getAtribute() {
        return atribute;
    }

    public static void setAtribute(String atribute) {
        Sort.atribute = atribute;
    }

}
